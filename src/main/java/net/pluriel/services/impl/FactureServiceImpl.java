package net.pluriel.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.FactureMapper;
import net.pluriel.dto.requests.FactureRequestDto;
import net.pluriel.dto.responses.FactureResponseDto;
import net.pluriel.entities.Facture;
import net.pluriel.entities.Order;
import net.pluriel.entities.PaymentFacture;
import net.pluriel.entities.Product;
import net.pluriel.exceptions.NotFound;
import net.pluriel.exceptions.RestException;
import net.pluriel.repositories.ClientRepository;
import net.pluriel.repositories.FactureRepository;
import net.pluriel.repositories.OrderRepository;
import net.pluriel.repositories.PaymentFactureRepository;
import net.pluriel.repositories.PaymentRepository;
import net.pluriel.repositories.ProductRepository;
import net.pluriel.services.FactureService;

@Service @Slf4j
public class FactureServiceImpl implements FactureService{

	@Autowired
	private FactureMapper factureMapper;
	
	@Autowired
	private FactureRepository factureRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentFactureRepository paymentFactureRepository;
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	
	private BigDecimal totatprice;
	private BigDecimal totalmontant;
	
	String badRequestmessage;
	
	

	public void validation(Facture facture) {
		badRequestmessage="";
		if (facture.getOrders()== null || facture.getOrders().size()==0) {
			badRequestmessage+="OrderList cannot be null or empty. ";
           
        }
		if (facture.getPaymentFactures()== null || facture.getPaymentFactures().size()==0) {
			badRequestmessage+="paymentInvoice List cannot be null or empty. ";
			//throw new BadRequest("paymentInvoice List cannot be null or empty");
        }
		
		
		if(totalVerification( facture)==false) {
			badRequestmessage+="total montant does not equal total price !!!!!   ";
		}
		List<Order> orders = facture.getOrders();
		List<PaymentFacture> paymentFactures = facture.getPaymentFactures();
		orders.stream().forEach(order -> {
			if(order.getClient() == null   ) {
				 throw new NotFound("Client cannot be null or empty ");
			 }
			if(order.getProduct()==null) {
				throw new NotFound("Product cannot be null or empty ");
			}
			 if(order.getQuantity() <= 0) {
				 badRequestmessage+="Quantity cannot be equal or lower than 0. ";
				
			 }
			
			 clientRepository.findById(order.getClient().getId()).orElseThrow(() -> new NotFound("client does not exist"));
			 productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new NotFound("Product  does not exist"));
			
		});
		paymentFactures.stream().forEach(paymentFacture -> {
			if(paymentFacture.getPayment() == null) {
				throw new NotFound("Payment cannot be null or empty. "); 
			 }
			paymentRepository.findById(paymentFacture.getPayment().getId()).orElseThrow(() -> new NotFound("Payment  does not exist"));

			
		});
		
		if(badRequestmessage!="") {
			throw new RestException(badRequestmessage);
		}		
		
	}
	
	
   public boolean totalVerification(Facture facture) {
        
        totatprice=new BigDecimal("0.0");
        totalmontant=new BigDecimal("0.0");
        facture.getOrders().stream().forEach(order -> { 
             Product product =productRepository.findById(order.getProduct().getId()).orElseThrow(() -> new NotFound("Product  does not exist"));
             totatprice = totatprice.add(product.getPrice().multiply(new BigDecimal(order.getQuantity())));

                     
        });
         
        facture.getPaymentFactures().stream().forEach(paymenFacture -> {
                
        	
        	totalmontant=totalmontant.add(paymenFacture.getMontant());
                     
        });
        
         if(totatprice.compareTo(totalmontant) == 0) {
             return true;
             
         }
        
        return false;
    }

	
	@Override
	@Transactional
	public FactureResponseDto create(FactureRequestDto factureRequestDto) {
		
		Facture facture = factureMapper.convertRequestToEntity(factureRequestDto);
		
		validation(facture);
		List<Order> orders = facture.getOrders();
		List<PaymentFacture> paymentFactures = facture.getPaymentFactures();
		
		facture.setOrders(new ArrayList<>());
		facture.setPaymentFactures(new ArrayList<>());
		
		
		factureRepository.save(facture);
		
		orders.stream().forEach(order -> {
		
			order.setId(null);
			// ---------------------- Check ClientId, productId ------------
			order.setFacture(facture);
		});
		paymentFactures.stream().forEach(paymentFacture -> {
	
			paymentFacture.setId(null);
			// ---------------------- Check ClientId, productId ------------
			paymentFacture.setFacture(facture);
		});
		
		
		facture.setOrders(orders);
		facture.setPaymentFactures(paymentFactures);
		
		
		
		
		
		return factureMapper.convertEntityToResponse(facture);
	}

	@SneakyThrows
	@Override
	public FactureResponseDto getOne(Integer id) {
		Facture facture = factureRepository.findById(id).orElseThrow(() -> new Exception("Facture non trouvée"));
		return factureMapper.convertEntityToResponse(facture);
	}

	@SneakyThrows
	@Override
	@Transactional
	public FactureResponseDto update(FactureRequestDto factureRequestDto, Integer id) {
		Facture facture = factureRepository.findById(id).orElseThrow(() -> new Exception("Facture non trouvée"));
		Facture factureRequest = factureMapper.convertRequestToEntity(factureRequestDto);
		validation(factureRequest);
		
		/*factureRequest.getOrders().stream().forEach(order -> {
			// ---------------------- Check ClientId, productId ------------
			order.setFacture(facture);
		});*/
		/* salam ssi yassine hadi li fuha 2 dyal les boucle drnaha bach finma tm7a chi order flupdate taytm7a 7ta flbase de donne 
		 * w3lach makhdmnach bstream 7it fstream mamymknch dir break 
		 * wl9ina 2dyal solusions khrin ya ima ndiro query frepository bach nm7iw hadok li zaydin fdatabase 
		 * ola nzido 2 dyal les liste f facture request fihom les ids dyal order/paymentfacture li aytm7aw ofach tatdkhl lhna 
		 * tatm7im bdeleteAll(idsToDelete)  */
		
		List<Order> ordersToDelete = new ArrayList<>();
        for (Order existingOrder : facture.getOrders()) {
            boolean found = false;
            for (Order updatedOrder : factureRequest.getOrders()) {
            	
            	updatedOrder.setFacture(facture);

                if (existingOrder.getId() == updatedOrder.getId()) {
                    found = true;
                    break;
                }
                
            }
            if (!found) {
                ordersToDelete.add(existingOrder);
            }
        }
        orderRepository.deleteAll(ordersToDelete);
        
        
        List<PaymentFacture> PaymentFacturesToDelete = new ArrayList<>();
        for (PaymentFacture existingPaymentFacture : facture.getPaymentFactures()) {
            boolean found = false;
            for (PaymentFacture updatedPaymentFacture : factureRequest.getPaymentFactures()) {
            	
            	updatedPaymentFacture.setFacture(facture);

                if (existingPaymentFacture.getId() == updatedPaymentFacture.getId()) {
                    found = true;
                    break;
                }
                
            }
            if (!found) {
                PaymentFacturesToDelete.add(existingPaymentFacture);
            }
        }
        paymentFactureRepository.deleteAll(PaymentFacturesToDelete);
		
		facture.setDatePaymentFacture(factureRequest.getDatePaymentFacture());
		facture.setOrders(factureRequest.getOrders());
		facture.setPaymentFactures(factureRequest.getPaymentFactures());
		
		factureRepository.save(facture);
		

		
		
		
		
		return factureMapper.convertEntityToResponse(facture);
	}

	@Override
	public Page<FactureResponseDto> getAll(int page, int size) {
		return factureRepository.findAll(PageRequest.of(page, size)).map(factureMapper::convertEntityToResponse);
	}

	@Override
	public Page<FactureResponseDto> getAllByClient(Integer clientId, int page, int size) {
		return factureRepository.findAll(PageRequest.of(page, size)).map(factureMapper::convertEntityToResponse);
	}

}
