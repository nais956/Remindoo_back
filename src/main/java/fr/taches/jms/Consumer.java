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
import fr.taches.domain.Liste;
import fr.taches.domain.Note;
import fr.taches.domain.Tache;
import fr.taches.domain.TypeTache;
import fr.taches.service.ServiceListe;

public class Consumer implements Runnable {

	@Autowired
	private Producer producer;

	@Autowired
	private ServiceListe ServiceListe;

	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
	private QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
	private Queue queue = (Queue) applicationContext.getBean("queueDemandes");
	private QueueConnection connection;
	private QueueSession session;
	private ObjectMessage message;
	private Demande demande;
	private Demande reponse = demande;
	private String typeDemande;
	private Object contenu;


	private void repondre() {
		producer.sendReponse(reponse);
	}

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

				demande = (Demande) message.getObject();
				typeDemande = demande.getTypeDemande();
				contenu = demande.getContenu();

				switch(typeDemande) {
				case "getListes":
					reponse.setReponse(ServiceListe.listListe());
					repondre();
					break;
				case "createListe":
					ServiceListe.createListe((Liste) contenu);
					break;
				case "updateListe":
					ServiceListe.updateListe((Liste) contenu, demande.getId());
					break;
				case "deleteListe":
					ServiceListe.deleteNote(demande.getId());
					break;
				case "getAllNotes":
					reponse.setReponse(ServiceListe.getAllNotes(demande.getId()));
					repondre();
					break;
				case "getAllTaches":
					reponse.setReponse(ServiceListe.getAllTaches(demande.getId()));
					repondre();
					break;
				case "getNote":
					reponse.setReponse(ServiceListe.findNoteById(demande.getId()));
					repondre();
					break;
				case "postNote":
					ServiceListe.createNote(demande.getId(), (Note) contenu);
					break;
				case "updateNote":
					ServiceListe.updateNote((Note) contenu, demande.getId());
					break;
				case "deleteNote":
					ServiceListe.deleteNote(demande.getId());
					break;
				case "postTache":
					ServiceListe.createTache(demande.getId(), (Tache) contenu);
					break;
				case "updateTache":
					ServiceListe.updateTache((Tache) contenu, demande.getId());
					break;
				case "deleteTache":
					ServiceListe.deleteTache(demande.getId());
					break;
				case "getTypeTaches":
					reponse.setReponse(ServiceListe.listTypeTache());
					repondre();
					break;
				case "postTypeTache":
					ServiceListe.createTypeTache((TypeTache) contenu);
					break;
				case "updateTypeTache":
					ServiceListe.updateTypeTache((TypeTache) contenu, demande.getId());
					break;
				case "deleteTypeTache":
					ServiceListe.deleteTypeTache(demande.getId());
					break;
				case "getCategories":
					reponse.setReponse(ServiceListe.listCategories());
					repondre();
					break;
				default:

					break;
				}
				producer.sendReponse(reponse);



				System.out.println("***** Message traité *****");
			}
		}catch(Exception e){e.printStackTrace();}
	}

}