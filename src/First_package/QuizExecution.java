package First_package;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/* application Quiz by akram Reqqi */

class QuizExecution extends JFrame implements ActionListener{
	
	JLabel lbl;
	JRadioButton Rb[]= new JRadioButton[4];

	String[] reponseCorrect= {"Compilé et interpreté", "Object", "commence par une majuscule","oui","oui", "Interface", 
							                      ".Class"," aucun des choix", "vrai", "final","this","surchargée","vrai","vrai","vrai", " a=10 b=0 Je suis à l'interieur de finally"};
	
	static JButton b1;
	static JButton v1;
	ButtonGroup bg;
	int count=0, current=0;	

	JLabel label  = new JLabel("",SwingConstants.CENTER);
	static JLabel label1  = new JLabel("");
	
	//JPanel panel = new JPanel();
	static JTextArea textChrono = new JTextArea();
	Font f = new Font("Serif", Font.BOLD, 24);

	
	static Timer timer = new Timer();
	static int i = 120;
	
	QuizExecution(String s) throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		super(s);
		//panel.add(textChrono);
		textChrono.setBounds(250,5,80,30); 
		textChrono.setFont(f);
		add(textChrono);
		lbl = new JLabel();
		add(lbl);
		
		label1.setBounds(180, 250, 250, 30);
		add(label1); 
		Font  f1 = new Font("Serif", Font.BOLD,18);
		label1.setFont(f1);
		label1.setForeground(Color.RED);
		
 		bg= new ButtonGroup();
		
		for( int i=0; i< Rb.length; i++){
			Rb[i] = new JRadioButton();
			System.out.println(Rb.length);
			if(i < 2) {
			add(Rb[i]);
			Rb[i].addActionListener(this);
			bg.add(Rb[i]);
			}
			else if(i < 3) {
				add(Rb[i]);
				Rb[i].addActionListener(this);
				bg.add(Rb[i]);
				}
		}
		
		b1=new JButton("Next");
		b1.addActionListener(this);
		add(b1);
		set();
		
		label.setBounds(1000, 40, 150, 30);
		add(label); 
		
		lbl.setBounds(350,60,2000,3400);
		Rb[0].setBounds(50,80,100,20);
		Rb[1].setBounds(50,110,100,20);
		Rb[2].setBounds(50,140,100,20);
		b1.setBounds(120,300,100,30);
		label.setBackground(Color.black);
		
		v1=new JButton("Valider");
		v1.addActionListener(this);
		add(v1);
		set();
		v1.setBounds(350,300,100,30);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(250,100);
		setVisible(true);
		setSize(600,400);	
	}
	
	
	static TimerTask task = new TimerTask(){
		public void run(){
			String time = "0";
			try {
				time = getTime(i);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textChrono.setText(time);
			i--;
		}
	};
	public static void runTimer(){
		
			timer.schedule(task, 0, 1000 );	
		
	}
	
	static String getTime(int sec) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		
	    int minutes = 0;
	    int seconds = 0;
	    
	    //pour afficher
	    String strMins; 
	    String strSecs;

	    if(i==295) {
	    		label1.setText("Attention il te reste 30 sec");
	    }
	    if(i==0) {
	    	b1.setEnabled(false);
	    	v1.setEnabled(false);
	    	//System.exit(0);	
	    }
	   if (sec >= 60){
		   
	        minutes = sec / 60;
	        seconds = sec % 60;
	    }

	    else if (sec < 60){
	    	
	        minutes = 0;
	        seconds = sec;
	    }
	    
	     

	    if(seconds < 10)
	    	strSecs = "0" + Integer.toString(seconds);
	    else
	    	strSecs = Integer.toString(seconds);
	   
	    if(minutes < 10)
	   	 strMins = "0" + Integer.toString(minutes);
	   else
		   strMins = Integer.toString(minutes);
	    
	    	
	        
	    String time =strMins + ":" + strSecs;
	    return time;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b1){
			current++;
			set();
			if(current==4){
				b1.setEnabled(false);
			}
		}
		if(count >= 40) {
			if(e.getSource() == v1) {
				current++;
				set();
				b1.setEnabled(true);
				label.setText("");
				label.setText("Score : "+count);
				
			}
			if(current== 9) {
				
				System.out.println("current" + current);
				b1.setEnabled(false);
			}
			if(current == 15) {
				System.out.println("current" + current);
				b1.setEnabled(false);
				
				label.setText("");
				label.setText("Bravo!!!!!!!!!!");
				
			}
		}

		for(int i=0 ; i < reponseCorrect.length; i++) {
			if(e.getActionCommand().equals(reponseCorrect[i])){
				count += 20;
			}
		}
		
		System.out.println(" Score ="+count);
		
		
	}

	
	void set(){

		if(current==0){
			lbl.setText("<html><h1 style='color:blue;'>Question 1 :</h1> JAVA est un langage</html>");
			Rb[0].setText("Compilé");
			Rb[1].setText("Interprété"); 
			Rb[2].setText("Compilé et interpreté");	
		}
		
		if(current==1){
			lbl.setText("<html>Que2:<br> Toutes les classes héritent de la classe</html>");
			Rb[0].setText("Main");
			Rb[1].setText("Object");
			Rb[2].setText("AWT");
			
		}
	
		if(current==2){
			lbl.setText("<html>Que3:<br> Par convention une classe</html>");
			Rb[0].setText("est en minuscule");
			Rb[1].setText("commence par une majuscule");
			Rb[2].setText("est en majuscules");
		}
		
		if(current==3){
			lbl.setText("<html>Que4:<br> Est-ce que on peut avoir plusieurs constructeurs pour la même classe </html>");
			Rb[0].setText("oui");
			Rb[1].setText("non");
			Rb[2].setVisible(false);
		}
		
		lbl.setBounds(60,30,850,100);
		for(int i=0,j=0;i<=90;i+=30,j++)
			Rb[j].setBounds(100,140+i,200,20);
		}
	
	public void vider() {
		Rb[0].setSelected(false);
		Rb[1].setSelected(false);
		Rb[2].setSelected(false);
	}

	public static void main(String s[])throws LineUnavailableException, UnsupportedAudioFileException, IOException{
			
		new QuizExecution("Online Test Of Java");
		runTimer();
		
		
	}

}
