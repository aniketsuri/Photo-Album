import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;

public class PictureLoader {

	private JFrame frame;
	java.io.File [] file;
	String name[];
	String annotation[];
	int index = 0;
	int currentIndex = -1;
	JPanel panel;
	JPanel panel_1;
	JPanel panel_2;
	JPanel panel_3;
	JLabel picLabel;
	JLabel lblNoMorePics;
	JLabel lblNoMorePics_1;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblName_1;
	JLabel lblName_2;
	JLabel lblAnnotation_1;
	JLabel lblAnnotation_2;
	//JFileChooser  fileDialog = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PictureLoader window = new PictureLoader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PictureLoader() {
		file = new File[10];
		name = new String[10];
		annotation = new String[10];
			initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize()  {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
	    panel = new JPanel();
		frame.getContentPane().add(panel, "name_2085431446561320");
		panel.setLayout(null);
        panel.setVisible(true);  

	    panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_2085434580196206");
		panel_1.setLayout(null);
		
		panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, "name_2101950678030017");
		panel_3.setLayout(null);
		
		JLabel lblClickOnA = new JLabel("Click on a pic name to view it");
		lblClickOnA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClickOnA.setBounds(34, 11, 289, 24);
		panel_3.add(lblClickOnA);
		
	    panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_2099317022147304");
		panel_2.setLayout(null);

		lblNoMorePics = new JLabel("no more pics!");
		lblNoMorePics.setBounds(305, 190, 99, 14);
		panel_1.add(lblNoMorePics);
		lblNoMorePics.setVisible(false);
		
	    lblNoMorePics_1 = new JLabel("no more pics!");
		lblNoMorePics_1.setBounds(42, 190, 99, 14);
		panel_1.add(lblNoMorePics_1);
		lblNoMorePics_1.setVisible(false);
		
		JButton btnPrevious = new JButton("previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(currentIndex > 0){
				currentIndex--;
				 lblNoMorePics_1.setVisible(false);
				 lblNoMorePics.setVisible(false);
				 showImage();
			}
			
			else lblNoMorePics_1.setVisible(true);
			}
		});
		btnPrevious.setBounds(42, 215, 99, 23);
		panel_1.add(btnPrevious);
		
		
		
		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(currentIndex < index-1){
					currentIndex++;
					lblNoMorePics.setVisible(false);
					lblNoMorePics_1.setVisible(false);
					showImage();
					}
			else lblNoMorePics.setVisible(true);
			}
		});
		btnNext.setBounds(305, 215, 99, 23);
		panel_1.add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			panel_1.setVisible(false);
			panel.setVisible(true);
			}
		});
		btnBack.setBounds(178, 215, 89, 23);
		panel_1.add(btnBack);

		//fileDialog = new JFileChooser();
		final JLabel lblStatus = new JLabel(" ");
	    lblStatus.setBounds(110, 11, 216, 14);
		panel_2.add(lblStatus);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(10, 66, 46, 14);
		panel_2.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(92, 63, 114, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblAnnotation = new JLabel("Annotation :");
		lblAnnotation.setBounds(10, 107, 80, 14);
		panel_2.add(lblAnnotation);
		
		textField_1 = new JTextField();
		textField_1.setBounds(92, 104, 250, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			name[index-1] = textField.getText();
			annotation[index-1] = textField_1.getText();
			panel_2.setVisible(false);
			panel.setVisible(true);
			}
		});
		btnSubmit.setBounds(92, 135, 89, 23);
		panel_2.add(btnSubmit);
			
		final JFileChooser  fileDialog = new JFileChooser();
	      JButton showFileDialogButton = new JButton("Upload Image");
	      showFileDialogButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            int returnVal = fileDialog.showOpenDialog(panel);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File tempfile = fileDialog.getSelectedFile();
	               file[index++] = tempfile;
	               lblStatus.setText(file[index-1].getName() + " Selected");
	               panel_2.setVisible(true);
		           panel.setVisible(false);
		           textField_1.setText("");
		           textField.setText(file[index-1].getName());
		           
	            }
	            else{
	            	lblStatus.setText("File selection cancelled");
	            }
	            
	            
	         }
	      });
	      showFileDialogButton.setBounds(31,102,143,40);
	      panel.add(showFileDialogButton);
	      
	      final JLabel lblFirstUploadPics = new JLabel("First upload pics!");
	      lblFirstUploadPics.setBounds(283, 153, 127, 14);
	      lblFirstUploadPics.setVisible(false);
	      panel.add(lblFirstUploadPics);
	      
	      JButton btnViewImages = new JButton("View Images");
	      btnViewImages.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      	if(index <= 0){ lblFirstUploadPics.setVisible(true); return; }
	      	lblFirstUploadPics.setVisible(false);
	      	currentIndex = 0;
	      //	showImage();
	      	panel.setVisible(false);
	     // 	panel_1.setVisible(true);
	      	ViewImageList();
	      	
	      	}
	      });
	      btnViewImages.setBounds(254, 102, 143, 40);
	      panel.add(btnViewImages);
	      
	      JLabel lblWelcomeToGallery = new JLabel("Welcome to Gallery");
	      lblWelcomeToGallery.setFont(new Font("Tahoma", Font.BOLD, 16));
	      lblWelcomeToGallery.setBounds(130, 11, 195, 31);
	      panel.add(lblWelcomeToGallery);
	      
	      
	      
	      BufferedImage img=null;
			try {
				img = ImageIO.read(new File("A.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedImage dimg = toBufferedImage(img.getScaledInstance(150, 150,
			        Image.SCALE_SMOOTH));
			picLabel = new JLabel(new ImageIcon(dimg));
			picLabel.setBounds(136, 45, 150, 150);
			panel_1.add(picLabel);
			
			lblName_1 = new JLabel("name : ");
			lblName_1.setBounds(10, 11, 46, 14);
			panel_1.add(lblName_1);
			
			lblName_2 = new JLabel("NAME");
			lblName_2.setBounds(95, 11, 106, 14);
			panel_1.add(lblName_2);
			
			lblAnnotation_1 = new JLabel("annotation :");
			lblAnnotation_1.setBounds(10, 30, 89, 14);
			panel_1.add(lblAnnotation_1);
			
			lblAnnotation_2 = new JLabel("ANNOTATION");
			lblAnnotation_2.setBounds(95, 30, 200, 14);
			panel_1.add(lblAnnotation_2);
			
			
			
			

			

	}
	
	public void showImage(){
		
		BufferedImage img=null;
		try {
			img = ImageIO.read(file[currentIndex]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage dimg = toBufferedImage(img.getScaledInstance(150, 150,
		        Image.SCALE_SMOOTH));
		//picLabel = new JLabel(new ImageIcon(dimg));
		picLabel.setIcon(new ImageIcon(dimg));
		picLabel.setBounds(136, 45, 150, 150);
		panel_1.add(picLabel);
		picLabel.setVisible(true);
		lblName_2.setText(name[currentIndex]);
		lblAnnotation_2.setText(annotation[currentIndex]);
		panel_1.setVisible(true);
	}
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public void ViewImageList(){
		JButton [] button = new JButton[10];
		for( int i = 0; i < index; i++){
			final int j = i ;
			button[i] = new JButton(name[i]);
			button[i].addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent arg0) {
		      		currentIndex = j ;
		      		lblNoMorePics.setVisible(false);
		      		lblNoMorePics_1.setVisible(false);		      		
		      		panel_3.setVisible(false);
		      		showImage();
		      	}
		      });
			button[i].setBounds(100,50 + 22*i, 100, 18);
			panel_3.add(button[i]);	
		}
		panel_3.setVisible(true);
	}
	
	
}
