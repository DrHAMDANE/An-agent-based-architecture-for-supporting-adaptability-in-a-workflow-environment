import java.awt.*;

import javax.swing.*;
import jade.core.behaviours.*;
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;

import java.util.*;


public class MyAgent extends Agent{
	protected void setup() 
    {	String NomAgent;
		String serviceName="participant au système workflow";
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
 
  		 		 
// ------------Interface Agent
  			NomAgent=getLocalName();
  			//Create labels and text fields
  			JLabel lastName1 = new JLabel("Mon Nom :",JLabel.RIGHT );
  			JLabel lastName = new JLabel(NomAgent,JLabel.LEFT);
  			JLabel middleName = new JLabel("Service fourni", JLabel.RIGHT);
  			JTextField middleField = new JTextField(10);
  			
  			//Add displayedMnemonic and labelFor property values
  			lastName1.setDisplayedMnemonic('M');
  			middleName.setDisplayedMnemonic('T');
  			middleName.setLabelFor(middleField);
  			
  			//Layout and Display
  			JPanel p = new JPanel();
  			p.setLayout(new GridLayout(3,2,5,5));
  			p.add(lastName1);
  			p.add(lastName);
  			p.add(middleName);
  			p.add(middleField);
  						
  			JFrame f = new JFrame();
  			f.setSize(300,300);
  			//f.addWindowListener(new BasicWindowMonitor());
  			f.setContentPane(p);
  			f.pack();
  			f.setVisible(true);
//---------- traitement des messages
  			
  			MessageTrait comm= new MessageTrait();
  			addBehaviour(comm);	
  			
    }
	private class MessageTrait extends CyclicBehaviour{
		 
		public void action() {
			ACLMessage msg = myAgent.receive();
			
			if (msg != null) {
				//Message received. Process it...
				/*JLabel label = new JLabel("Mon Nom :",JLabel.RIGHT );
				JPanel p = new JPanel();
	  			p.setLayout(new GridLayout(3,2,5,5));
	  			p.add(label);
	  			JFrame f = new JFrame();
	  			f.setSize(300,300);
	  			f.setName("traitement de message");
	  			//f.addWindowListener(new BasicWindowMonitor());
	  			f.setContentPane(p);
	  			f.pack();
	  			f.setVisible(true);*/
				
	  			
			}
				else {
				block();
				}
			}
			
		} 
	
}
    



