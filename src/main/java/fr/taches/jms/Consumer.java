package fr.taches.jms;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.taches.domain.Demande;

public class Consumer implements Runnable {

	@Autowired
	private Producer producer;
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
	private QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
	private Queue queue = (Queue) applicationContext.getBean("queueDemandess");
	private QueueConnection connection;
	private QueueSession session;
	private ObjectMessage message;
	private Demande demande;
	private Demande reponse;


	public void run() {
		try{
			System.out.println("***** Consumer lancé *****");
			// connection au message broker
			connection = factory.createQueueConnection();

			// ouvrir session sans transaction (1 seul message) et acquitement automatique
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();

			QueueReceiver receiver = session.createReceiver(queue);

			// réception et traitement des réponses
			while(true) {
				message = (ObjectMessage) receiver.receive();	// bloque sur attente message
				System.out.println("***** Message reçu *****");
				
				/*reponse = (Demande) message.getObject();
				demande = demandesencours.getDemande(reponse.getNumero());
				if (!(demande == null) ) {
					synchronized (demande) {
						demande.setReponse(reponse.getReponse());
						demande.setDisponible();
						demande.notifyAll();
					}
				}*/
				producer.sendReponse(reponse);
				
				System.out.println("***** Message traité *****");
			}
		}catch(Exception e){e.printStackTrace();}
	}

}