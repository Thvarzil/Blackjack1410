package blackjack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * GUI and game handling class
 * 
 * @author Hayden Blackmer
 *
 *
 *Quick Reminder Notes:
 *
 *All of the relevant methods are non-static methods in the class GameState,
 *which means that they are attached to the instance, not the class. 
 *Therefore, in your button onclicks, you will be calling the appropriate functions
 *in the format gameState.method(), and then you'll just need to update the proper 
 *components with the new gameState information. 
 *
 *Summary of GameState methods you might need to call:
 *- deal() - returns an ArrayList<Card> that contains the player's first card at 0 and the dealer's
 *			first card at 1 - this will probably be called in the onclick of the Submit button
 *- hit() - returns the card dealt to the player
 *- stay() - returns an ArrayList of cards, the first of which is the dealer's hidden card
 *- modifyBet(int change) - takes the amount the bet is being changed and alters the 
 *			bankroll and bet accordingly - both the bet modification buttons will call this, with 
 *			different input
 *-evaluateHand() - determines who wins the hand. Returns 0 if player won, 1 if player lost, 2 if draw,
 *			3 when called after hit() indicating the player has not busted
 *
 *Buttons:
 ******Some of the methods you'll need to call throw exceptions intended to communicate things with you
 ******You'll want to catch these exceptions and modify the GUI appropriately. Also, evaluateHand() and 
 ******handling its output should always be the last thing in the onclick - all GUI changes due to cards
 ******and the like should be handled first.
 **Bet Up/Bet Down - calls modifyBet(int change). modifyBet throws two exceptions - IllegalArgumentException
 * 	if player tries to bet more than they have, and IllegalStateOperation if player tries to bet less than minimum 
 **Submit - calls deal(). deal() returns the player and dealer's first cards 
 **Hit - calls hit() and evaluateHand(). hit() returns the player's new card, evaluateHand() checks to see if 
 *	player has busted. will return 1 or 3. if returns 1, player has lost hand, else continue.
 **Stay - calls stay() and evaluateHand(). stay() returns the dealer's hand, with the hidden card last. evaluate hand
 *	will not return 3 in this case, as the player has stayed and the hand is over.
 **New Hand (when hand has ended) - calls startHand(). startHand() throws an IllegalStateOperation if player is
 *	unable to make minimum bet.
 *
 */
@SuppressWarnings("serial")
public class BlackjackGUI extends JFrame {

	private JPanel contentPane;
	private GameState gameState = new GameState();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackjackGUI frame = new BlackjackGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BlackjackGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = newLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
	
		
	}

	private JLayeredPane newLayeredPane() {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBackground(new Color(0, 102, 51));
		
		JPanel PlayersCards = newPlayersCards();
		layeredPane.add(PlayersCards);
		
		JPanel DealersCards = newDealersCards();
		layeredPane.add(DealersCards);

		JPanel BettingPanel = newBettingPanel();
		layeredPane.add(BettingPanel);
	
		JPanel panelEndOfGame = newPanelEndOfGame();
		
		layeredPane.add(panelEndOfGame);
		
		JPanel HitStayPanel = newHitStayPanel();
		layeredPane.add(HitStayPanel);
		{
			JLabel lblNewLabel = new JLabel("You Win!!");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 46));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 26, 285, 38);
			layeredPane.add(lblNewLabel);
		}
		
		return layeredPane;
	}

	private JPanel newHitStayPanel() {
		JPanel HitStayPanel = new JPanel();
		HitStayPanel.setBounds(47, 202, 217, 38);
		HitStayPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnHit = newBtnHit();
		HitStayPanel.add(btnHit);
	
		JButton btnStay = newBtnStay();
		HitStayPanel.add(btnStay);
		
		return HitStayPanel;
	}

	private JButton newBtnStay() {
		JButton btnStay = new JButton("Stay");
		btnStay.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnStay;
	}

	private JButton newBtnHit() {
		JButton btnHit = new JButton("Hit");
		btnHit.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnHit;
	}

	private JPanel newPanelEndOfGame() {
		JPanel panelEndOfGame = new JPanel();
		panelEndOfGame.setBounds(47, 75, 217, 38);
		panelEndOfGame.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnPlayAgain = newbtnPlayAgain();
		panelEndOfGame.add(btnPlayAgain);
	
		JButton btnClose = newbtnClose();
		panelEndOfGame.add(btnClose);
		
		return panelEndOfGame;
	}

	private JButton newbtnClose() {
		JButton btnClose = new JButton("End");
		btnClose.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnClose.addActionListener(e -> System.exit(0));

		return btnClose;
	}

	private JButton newbtnPlayAgain() {
		JButton btnPlayAgain = new JButton("Next Hand");
		btnPlayAgain.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnPlayAgain;
	}

	private JPanel newBettingPanel() {
		JPanel BettingPanel = new JPanel();
		BettingPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		BettingPanel.setBounds(90, 290, 134, 113);
		BettingPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel BidPanel = newBidPanel();
		BettingPanel.add(BidPanel);
		
		JButton btnIncreaseBet = newbtnIncreaseBet();
		BettingPanel.add(btnIncreaseBet);

		JButton btnDecreaseBet = newbtnDecreaseBet();
		BettingPanel.add(btnDecreaseBet);
		
		JButton btnSubmit = newbtnSubmit();
		BettingPanel.add(btnSubmit);
		
		return BettingPanel;
	}

	private JPanel newBidPanel() {
		JPanel BidPanel = new JPanel();
		BidPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		BidPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel CurrentBet = newCurrentBet();
		BidPanel.add(CurrentBet);
		
		return BidPanel;
	}

	private JLabel newCurrentBet() {
		JLabel CurrentBet = new JLabel("Current Bet: 100");
		CurrentBet.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return CurrentBet;
	}

	private JButton newbtnSubmit() {
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnSubmit;
	}

	private JButton newbtnDecreaseBet() {
		JButton btnDecreaseBet = new JButton("\u25BC");
		return btnDecreaseBet;
	}

	private JButton newbtnIncreaseBet() {
		JButton btnIncreaseBet = new JButton("\u25B2");
		return btnIncreaseBet;
	}

	private JPanel newPlayersCards() {
		JPanel PlayersCards = new PlayersCards();
		PlayersCards.setBounds(320, 256, 335, 182);
		return PlayersCards;
	}
	
	private JPanel newDealersCards() {
		JPanel DealersCards = new DealersCards();
		DealersCards.setBounds(320, 11, 335, 182);
		return DealersCards;
	}
}
