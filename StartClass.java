//import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;

public class StartClass {

	//private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static int countMessages = 0;
	private static final Random r = new Random();
	private final static List<MessageType> allMessages = new ArrayList<>();
	private final static List<ManageSale> allSales = new ArrayList<>();
	private final static Map<String, Double> valReport = new HashMap<>();
	private final static Map<String, Integer> occurrencesReport = new HashMap<>();

	private static String getRandomProductType() {
		final int productType = Math.abs(r.nextInt(8) + 1);

		switch(productType) {
		case 1:{
			return "apple";
		}
		case 2:{
			return "orange";
		}
		case 3:{
			return "banana";
		}
		case 4:{
			return "olive";
		}
		case 5:{
			return "peach";
		}
		case 6:{
			return "broccoli";
		}
		case 7:{
			return "oregano";
		}
		case 9:{
			return "carrot";
		}
		default:{
			return "avocado";
		}
		}
	}

	private static void createMessageType_1(){
		final MessageType_1 message = new MessageType_1(getRandomProductType(), Math.abs(r.nextInt(345) + 1));
		allMessages.add(message);
	}

	private static void createMessageType_2(){
		final MessageType_2 message = new MessageType_2(getRandomProductType(), Math.abs(r.nextInt(345) + 1), Math.abs(r.nextInt(11) + 1));
		allMessages.add(message);
	}

	private static void createMessageType_3(){

		final int messageType = Math.abs(r.nextInt(3) + 1);
		MessageType_3 message = null;

		switch(messageType) {
		case 1:{
			message = new MessageType_3(getRandomProductType(), Math.abs(r.nextInt(999) + 1), AdjustmentOperation.add);
			break;
		}
		case 2:{
			message = new MessageType_3(getRandomProductType(), Math.abs(r.nextInt(123) + 1), AdjustmentOperation.subtract);
			break;
		}
		case 3:{
			message = new MessageType_3(getRandomProductType(), Math.abs(r.nextInt(5) + 1), AdjustmentOperation.multiply);
			break;
		}
		}

		allMessages.add(message);
	}

	private static void processMessage(final MessageType message){
		if(message != null) {
			if(message instanceof MessageType_1) {

				MessageType_1 message_1 = (MessageType_1)message;

				if(message_1 != null) {
					Sale sale = message_1.getSale();
					if(sale != null) {
						ManageSale ms = new ManageSale();
						ms.setSale(sale);
						ms.setOccurrencesNumber(1);
						allSales.add(ms);
					}
				}
			} else if(message instanceof MessageType_2) {

				MessageType_2 message_2 = (MessageType_2)message;

				if(message_2 != null) {
					Sale sale = message_2.getSale();
					if(sale != null) {
						ManageSale ms = new ManageSale();
						ms.setSale(sale);
						ms.setOccurrencesNumber(message_2.getOccurrencesNumber());
						allSales.add(ms);
					}
				}
			} else if(message instanceof MessageType_3) {
				MessageType_3 message_3 = (MessageType_3)message;

				if(message_3 != null) {
					Sale sale = message_3.getSale();
					if(sale != null) {
						ManageSale ms = new ManageSale();
						ms.setSale(sale);
						ms.setAdOp(message_3.getAdOp());
						ms.setOccurrencesNumber(1);
						allSales.add(ms);
					}
				}
			}
		}
	}

	private static void createSimpleReport() {
		if(allMessages != null && !allMessages.isEmpty()) {
			allMessages.forEach(message -> processMessage(message));
		}

		if(allSales != null && !allSales.isEmpty()) {
			for(ManageSale ms : allSales) {
				if(ms != null && ms.getAdOp() == null) {

					Sale sale = ms.getSale();

					if(sale != null) {
						String productType = sale.getProductType();
						if(productType != null && !productType.trim().isEmpty()) {
							double value = sale.getValue();
							if(value > 0) {
								int occurrencesNumber = ms.getOccurrencesNumber();

								if(occurrencesNumber > 0) {
									value = value * occurrencesNumber;
								}

								if(valReport.containsKey(productType)) {
									valReport.put(productType, valReport.get(productType) + value);
								}
								else {
									valReport.put(productType, value);
								}

								if(occurrencesReport.containsKey(productType)) {
									occurrencesReport.put(productType, occurrencesReport.get(productType) + occurrencesNumber);
								}
								else {
									occurrencesReport.put(productType, occurrencesNumber);
								}	
							}
						}
					}
				}
			}
			printReport(valReport, occurrencesReport);
		}
	}

	private static void printReport(Map<String, Double> valReport, Map<String, Integer> occurrencesReport) {
		if(valReport != null && !valReport.isEmpty() && occurrencesReport != null && !occurrencesReport.isEmpty()) {

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Product type       Total value        Number of occurrences");

			valReport.forEach((productType,v)-> {
				if(productType != null && occurrencesReport.containsKey(productType)) {
					System.out.println("    " + productType + "                " + v + "                 " + occurrencesReport.get(productType));
				}
			});
		}
	}

	private static void createAdjustmentsReport(){
		if(allSales != null && !allSales.isEmpty() && 
				valReport != null && !valReport.isEmpty()
				) {

			for(ManageSale ms : allSales) {
				if(ms != null) {

					final AdjustmentOperation ao = ms.getAdOp();

					if(ao != null) {

						Sale sale  = ms.getSale();
						if(sale != null) {

							String adjustmentProductType = sale.getProductType();
							double value =  sale.getValue();

							switch(ao) {
							case add : {

								if(valReport.containsKey(adjustmentProductType)) {
									valReport.put(adjustmentProductType, valReport.get(adjustmentProductType) + value);
								}
								break;
							} 
							case subtract : {

								if(valReport.containsKey(adjustmentProductType)) {
									valReport.put(adjustmentProductType, valReport.get(adjustmentProductType) - value);
								}

								break;
							} 
							case multiply : {

								if(valReport.containsKey(adjustmentProductType)) {
									valReport.put(adjustmentProductType, valReport.get(adjustmentProductType) * value);
								}

								break;
							} 
							}
						}
					}
				}
			}

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(" NEW total adjustment report");

			printReport(valReport, occurrencesReport);
		}
	}

	private static void processingSale() {
		++countMessages;
		final int messageType = Math.abs(r.nextInt(3) + 1);

		switch(messageType) {
		case 1:{
			createMessageType_1();
			break;
		}
		case 2:{
			createMessageType_2();
			break;
		}
		case 3:{
			createMessageType_3();
			break;
		}
		}

		if(countMessages % 10 == 0) {
			createSimpleReport();
		}

		if(countMessages == 50) {
			createAdjustmentsReport();
			//scheduler.shutdownNow();
		}
	}

	public static void main(String[] args) {

		/*final Runnable input = new Runnable() {
			public void run() { processingSale(); }
		};

		scheduler.scheduleAtFixedRate(input, 0, 1, SECONDS);*/
		for(int i = 1 ; i<= 50 ; i++) {
			processingSale();
		}
	}
}