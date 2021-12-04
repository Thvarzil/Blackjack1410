package blackjack;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * GUI Fragment representing Dealer's Hand
 * 
 * @author Hayden Blackmer
 *
 */
@SuppressWarnings("serial")
public class DealersCards extends JPanel {
	public String card1Path = "";
	public String card2Path = "";
	public String card3Path = "";
	public String card4Path = "";
	public String card5Path = "";
	public String card6Path = "";
	public String card7Path = "/img/1_back_of_card.jpg";
	public String card8Path = "/img/10_of_clubs.png";
	
	public DealersCards() {
		setBackground(new Color(0, 100, 0));
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		
		JLabel card1 = newCard1();
		layeredPane.add(card1);
		
		JLabel card2 = newCard2();
		layeredPane.add(card2);
		
		JLabel card3 = newCard3();
		layeredPane.add(card3);
		
		JLabel card4 = newCard4();
		layeredPane.add(card4);
		
		JLabel card5 = newCard5();
		layeredPane.add(card5);

		JLabel card6 = newCard6();
		layeredPane.add(card6);
		
		JLabel card7 = newCard7();
		layeredPane.add(card7);
		
		JLabel card8 = newCard8();
		layeredPane.add(card8);
	}

	private JLabel newCard8() {
		JLabel card8 = new JLabel("");
		card8.setIcon(new ImageIcon(PlayersCards.class.getResource(card8Path)));
		card8.setBounds(0, 0, 125, 182);
		return card8;
	}

	private JLabel newCard7() {
		JLabel card7 = new JLabel("");
		card7.setIcon(new ImageIcon(PlayersCards.class.getResource(card7Path)));
		card7.setBounds(30, 0, 125, 182);
		return card7;
	}

	private JLabel newCard6() {
		JLabel card6 = new JLabel("");
		card6.setIcon(new ImageIcon(PlayersCards.class.getResource(card6Path)));
		card6.setBounds(60, 0, 125, 182);
		return card6;
	}

	private JLabel newCard5() {
		JLabel card5 = new JLabel("");
		card5.setIcon(new ImageIcon(PlayersCards.class.getResource(card5Path)));
		card5.setBounds(90, 0, 125, 182);
		return card5;
	}

	private JLabel newCard4() {
		JLabel card4 = new JLabel("");
		card4.setIcon(new ImageIcon(PlayersCards.class.getResource(card4Path)));
		card4.setBounds(120, 0, 125, 182);
		return card4;
	}

	private JLabel newCard3() {
		JLabel card3 = new JLabel("");
		card3.setIcon(new ImageIcon(PlayersCards.class.getResource(card3Path)));
		card3.setBounds(150, 0, 125, 182);
		return card3;
	}
	
	private JLabel newCard2() {
		JLabel card2 = new JLabel("");
		card2.setIcon(new ImageIcon(PlayersCards.class.getResource(card2Path)));
		card2.setBounds(180, 0, 125, 182);
		return card2;
	}
	
	private JLabel newCard1() {
		JLabel card1 = new JLabel("");
		card1.setIcon(new ImageIcon(PlayersCards.class.getResource(card1Path)));
		card1.setBounds(210, 0, 125, 182);
		return card1;
	}
}
