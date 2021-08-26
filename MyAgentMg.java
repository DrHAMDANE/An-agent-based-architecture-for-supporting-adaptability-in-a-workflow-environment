import java.awt.*;
import javax.swing.*;
import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;
import java.util.*;
import jade.core.behaviours.*;

public class MyAgentMg extends Agent{
	AID[] liste;
	DFAgentDescription[] results;
	private Vector sellerAgents = new Vector();
		protected void setup() 
	    {	
					
			
			String NomAgent;
			String serviceName="Gestionnaire du système workflow";
			DFAgentDescription dfd = new DFAgentDescription();
	  		dfd.setName(getAID());
	  		ServiceDescription sd = new ServiceDescription();
	  		sd.setName(serviceName);
	  		sd.addLanguages("XPDL");
	  		sd.setType("Compagnie d'assurance");
	  		sd.addProtocols("Contract-Net");
	  		sd.addOntologies("Ontologie Assurance");
	  		sd.addProperties(new Property("Paye", "Algérie"));
	  		dfd.addServices(sd);
	  		
	  		try {
	  			DFService.register(this, dfd );
	  			}catch (FIPAException fe) {}
	 
	  			Object[] args = getArguments();
	  			if (args != null && args.length > 0) {
	  			for (int i = 0; i < args.length; ++i) {
	  			AID seller = new AID((String) args[i], AID.ISLOCALNAME);
	  			sellerAgents.addElement(seller);
	  			}
	  			int taille; 
	  			taille=sellerAgents.size();
	  			System.out.println(taille);
	  				 
	  			
//	 ------------Interface Agent
	  			NomAgent=getLocalName();
	  			//Create labels and text fields
	  			JLabel lastName1 = new JLabel("Mon Nom :",JLabel.RIGHT );
	  			JLabel lastName = new JLabel(NomAgent,JLabel.RIGHT);
	  			JLabel middleName = new JLabel("Service fourni : ", JLabel.RIGHT);
	  			JLabel middleName2 = new JLabel("Gestionnaire du système workflow", JLabel.RIGHT);
	  		
	  		  			//Layout and Display
	  			
	  			JPanel p = new JPanel();
	  			p.setLayout(new GridLayout(3,2,5,5));
	  			p.add(lastName1);
	  			p.add(lastName);
	  			p.add(middleName);
	  			p.add(middleName2);
	  			//p.add(middleField);
	  						
	  			JFrame f = new JFrame();
	  			f.setSize(300,300);
	  			//f.addWindowListener(new BasicWindowMonitor());
	  			f.setContentPane(p);
	  			f.pack();
	  			f.setVisible(true);

//------------------------------------------------//	  			
	  		/*	try {
	  		  		
	  		  		results = DFService.search(this, dfd);
	  		  		if (results.length > 0) {
	  		  			liste = new AID[results.length];
	  		  			// System.out.println("Agent "+getLocalName()+" found the following"+  serviceType+" services:");
	  		  			for (int i = 0; i < results.length; ++i) {
	  		  				liste[i]=results[i].getName();
	  		  				System.out.println(liste[i].getName()  );
	  		  				DFAgentDescription dfd1 = results[i];
	  		  				AID provider = dfd1.getName();
  		
	  		  			}
	  		  			
	  		  		}
	  		
	  			}// end try
	  		  	catch (FIPAException fe) {
	  		  		fe.printStackTrace();}
	  		  	*/
	  			
	  			AppelOffre Appel= new AppelOffre();
	  			addBehaviour(Appel);	
	  		    }//end Setup
	    }
		private class AppelOffre extends OneShotBehaviour{
			 public void action() {
				 ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				 for(int i = 0; i < liste.length; ++i){
					 cfp.addReceiver(liste[i]);
					 } // end for
				cfp.setLanguage("<tache> operation</tache>");	
				 myAgent.send(cfp);
				    
			 }
		}
}




