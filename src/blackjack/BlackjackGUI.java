package blackjack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class BlackjackGUI extends JFrame {
	
	private JPanel contentPane;
	

	private JLabel currentBet;
	private JButton btnSubmit;
	private JButton btnIncreaseBet;
	private JButton btnDecreaseBet;
	

	private JPanel dealersCards;
	private JLayeredPane dealersCardsPane;
	
	private JPanel playersCards;
	
	private JLabel dealerCard8;
	private JLabel dealerCard7;
	private JLabel dealerCard6;
	private JLabel dealerCard5;
	private JLabel dealerCard4;
	private JLabel dealerCard3;
	private JLabel dealerCard2;
	private JLabel dealerCard1;

	private JLabel playerCard8;
	private JLabel playerCard7;
	private JLabel playerCard6;
	private JLabel playerCard5;
	private JLabel playerCard4;
	private JLabel playerCard3;
	private JLabel playerCard2;
	private JLabel playerCard1;
	
	private int hitCounter = 0;
	private int dealerHitCounter = 0;
	private GameState gameState = new GameState();
	
	private JLayeredPane playersCardsPane;
	private JButton btnHit;
	private JButton btnStay;
	private JButton btnPlayAgain;
	private ArrayList<Card> cards;
	private JLayeredPane layeredPane;
	private JLabel lblGameOutcome;
	private JLabel availableBalance;
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
		gameState.startHand();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JLayeredPane layeredPane = newLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
	}

	/**
	 * Creates the Layered pane that all of the parts of the GUI are dislpayed in
	 * @return
	 */
	private JLayeredPane newLayeredPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBackground(new Color(0, 100, 0));
		
		JPanel framePlayersCards = newPlayersCards();
		layeredPane.add(framePlayersCards);
		
		JPanel frameDealersCards = newDealersCards();
		layeredPane.add(frameDealersCards);
		
		JPanel BettingPanel = newBettingPanel();
		layeredPane.add(BettingPanel);
		
		JPanel HitStayPanel = newHitStayPanel();
		layeredPane.add(HitStayPanel);
		
		JPanel panelEndOfGame = newPanelEndOfGame();
		layeredPane.add(panelEndOfGame);
		
		JLabel newlblGameOutcome = newlblGameOutcome();
		layeredPane.add(newlblGameOutcome);
		
		return layeredPane;
	}

	/**
	 * Displays text at the top of the screen that is 
	 * dynamically updated with the outcome of the game.
	 */
	private JLabel newlblGameOutcome() {
		lblGameOutcome = new JLabel("");
		lblGameOutcome.setForeground(new Color(255, 255, 255));
		lblGameOutcome.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblGameOutcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOutcome.setBounds(10, 60, 300, 38);
		return lblGameOutcome;
	}

	/**
	 * Creates the panel that contains the stay button.
	 * @return
	 */
	private JPanel newHitStayPanel() {
		JPanel HitStayPanel = new JPanel();
		HitStayPanel.setBounds(10, 207, 300, 38);
		HitStayPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnHit = newBtnplayerHit();
		btnHit.setEnabled(false);
		HitStayPanel.add(btnHit);
	
		btnStay = newBtnStay();
		btnStay.setEnabled(false);
		HitStayPanel.add(btnStay);
		
		return HitStayPanel;
	}

	/**
	 * Creates a button which implements the same functionality as staying in blackjack:
	 * OnClick: prompts the dealer to draw cards if their total is less than 17 and less
	 * than the score of the player.
	 * @return
	 */
	private JButton newBtnStay() {
		JButton btnStay = new JButton("Stay");
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//flips the dealers card 
				dealerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cards.get(0).toString())));
				
				while (gameState.getDealerScore() < 17 && gameState.getDealerScore() < gameState.getPlayerScore()) {
					switch (dealerHitCounter){
					case 0:
						dealerCard6.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.stay().toString())));
						break;
					case 1:
						dealerCard5.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.stay().toString())));
						break;
					case 2:
						dealerCard4.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.stay().toString())));
						break;
					case 3:
						dealerCard3.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.stay().toString())));
						break;
					case 4:
						dealerCard2.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.stay().toString())));
						break;
					case 5:
						dealerCard1.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.stay().toString())));
						
						//disables hit button forcing them to click stay
						btnHit.setEnabled(false);
						break;
					}
					dealerHitCounter++;
				}
				gameEnd();
			}
		});
		btnStay.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnStay;
	}

	/**
	 * Creates a button which implements the same functionality as Hitting in blackjack
	 * OnClick: Each time, a new card is added to the players hand. Calculates if the 
	 * player busts and does not allow for further hits.
	 */
	private JButton newBtnplayerHit() {
		JButton btnHit = new JButton("Hit");
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (hitCounter){
					case 0:
						playerCard6.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.playerHit().toString())));
						break;
					case 1:
						playerCard5.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.playerHit().toString())));
						break;
					case 2:
						playerCard4.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.playerHit().toString())));
						break;
					case 3:
						playerCard3.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.playerHit().toString())));
						break;
					case 4:
						playerCard2.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.playerHit().toString())));
						break;
					case 5:
						playerCard1.setIcon(new ImageIcon(BlackjackGUI.class.getResource(gameState.playerHit().toString())));
						
						//disables hit button forcing them to click stay
						btnHit.setEnabled(false);
						break;
				}
				hitCounter++;
				
				//checks if blackjack or bust
				if(gameState.getPlayerScore() >= 21) {
					//flips the dealers card 
					dealerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cards.get(0).toString())));
					gameEnd();
				}
			}
		});
		btnHit.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnHit;
	}
	
	/**
	 * Creates a panel that contains the Play again button.
	 */
	private JPanel newPanelEndOfGame() {
		JPanel panelEndOfGame = new JPanel();
		panelEndOfGame.setBounds(10, 155, 300, 38);
		panelEndOfGame.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnPlayAgain = newbtnPlayAgain();
		panelEndOfGame.add(btnPlayAgain);
	
		JButton btnClose = newbtnClose();
		panelEndOfGame.add(btnClose);
		
		return panelEndOfGame;
	}

	/**
	 * Wraps up the game by doing the following: 
	 * 1.)Disables the buttons
	 * 2.)Checks and displays the outcome of the game 
	 * 3.)Updates the bankroll
	 * 4.)Disables the button if the player doesnt have enough funds to play again.
	 */
	private void gameEnd() {
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		btnPlayAgain.setEnabled(true);
		
		//checks if its a win, tie or loss. 
		gameState.updateBalance();	
		
		//updates label at the top
		lblGameOutcome.setText(gameState.getGameOutcome());
		
		//update the bankroll 
		availableBalance.setText("New Balance: " + gameState.getBankroll());
		currentBet.setText("");
		
		if(gameState.getBankroll() <= GameState.MINIMUM_BET) {
			btnPlayAgain.setEnabled(false);
		}
	}
	
	/**
	 * Closes the application
	 */
	private JButton newbtnClose() {
		JButton btnClose = new JButton("End");
		btnClose.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnClose.addActionListener(e -> System.exit(0));

		return btnClose;
	}

	/**
	 * Creates a button which implements the same functionality as playing in blackjack:
	 * OnClick:
	 * 1.)Creates a new hand
	 * 2.)Disables play again button
	 * 3.)Enables Increase Bet , Decrease Bet, and Submit button
	 * 4.)Clears the image paths for the cards
	 * 5.)Clears the previous game outcome
	 * 6.)Updates the display of the current balance
	 * 7.)Updates the display of the current bet 
	 * 8.)Resets the number of times that the dealer and player have hit to 0
	 * @return
	 */
	private JButton newbtnPlayAgain() {
		btnPlayAgain = new JButton("Next Hand");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//start game again
				gameState.startHand();
				
				//Disable next hand 
				btnPlayAgain.setEnabled(false);
				
				//Enable submit btn inc bet, dec bet
				btnIncreaseBet.setEnabled(true);
				btnDecreaseBet.setEnabled(true);
				btnSubmit.setEnabled(true);
				
				//Set all card img paths to � �
				playerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard7.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard6.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard5.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard4.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard3.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard2.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				playerCard1.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				
				dealerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard7.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard6.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard5.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard4.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard3.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard2.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));
				dealerCard1.setIcon(new ImageIcon(BlackjackGUI.class.getResource("")));

				//update game outcome
				lblGameOutcome.setText("");
				
				//update balance
				availableBalance.setText("Balance : " + gameState.getBankroll());
				currentBet.setText("Current Bet: " + gameState.getCurrentBet());
				
				//reset card counters
				hitCounter = 0;
				dealerHitCounter = 0;
			}
		});
		btnPlayAgain.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnPlayAgain.setEnabled(false);
		return btnPlayAgain;
	}

	/**
	 * Creates the panel in the bottom left that contains the following:
	 * 1.)Display of the balance
	 * 2.)Display of the current bet
	 * 3.)Increase button
	 * 4.)Decrease button
	 * @return
	 */
	private JPanel newBettingPanel() {
		JPanel BettingPanel = new JPanel();
		BettingPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		BettingPanel.setBounds(10, 256, 300, 182);
		BettingPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel BalancePanel = newBalancePanel();
		BettingPanel.add(BalancePanel);
		
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

	/**
	 * Button that decreases the amount of the bet every time it is clicked.
	 */
	private JButton newbtnDecreaseBet() {
		btnDecreaseBet = new JButton("\u25BC");
		btnDecreaseBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Decreases the amount of the bet
				gameState.modifyBet(-10);
				if (gameState.getCurrentBet() <= 90) {
					currentBet.setText("Current Bet:  "+gameState.getCurrentBet());
					availableBalance.setText("Balance: " + gameState.getBankroll());
				}
				else {
					currentBet.setText("Current Bet: "+gameState.getCurrentBet());
					availableBalance.setText("Balance: " + gameState.getBankroll());
				}
			}
		});
		return btnDecreaseBet;
	}
	
	/**
	 * Button that increases the amount of the bet every time it is clicked.
	 * @return
	 */
	private JButton newbtnIncreaseBet() {
		btnIncreaseBet = new JButton("\u25B2");
		btnIncreaseBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Increases the amount of the bet
				gameState.modifyBet(10);
				if (gameState.getCurrentBet() <= 90) {
					currentBet.setText("Current Bet:  "+gameState.getCurrentBet());
					availableBalance.setText("Balance: " + gameState.getBankroll());
				}
				else {
					currentBet.setText("Current Bet: "+gameState.getCurrentBet());
					availableBalance.setText("Balance: " + gameState.getBankroll());
				}
			}
		});
		return btnIncreaseBet;
	}

	/**
	 * Displays the current available balance after the current bet amount is subtracted 
	 * from the total game balance.
	 * @return
	 */
	private JLabel newAvailableBalance() {
		availableBalance = new JLabel("Balance: " + gameState.getBankroll());
		availableBalance.setFont(new Font("Monospaced", Font.ITALIC, 15));
		availableBalance.setHorizontalAlignment(SwingConstants.CENTER);
		return availableBalance;
	}
	
	/**
	 * Displays the current bet.
	 * @return
	 */
	private JLabel newCurrentBet() {
		currentBet = new JLabel("Current Bet:  " + gameState.getCurrentBet());
		currentBet.setHorizontalAlignment(SwingConstants.CENTER);
		currentBet.setFont(new Font("Monospaced", Font.BOLD, 19));
		return currentBet;
	}
	
	/**
	 * Panel contained by the Betting panel that contains the 
	 * text displayed by the avalableBalance panel.
	 * @return
	 */
	private JPanel newBalancePanel() {
		JPanel BalancePanel = new JPanel();
		BalancePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel availableBalance = newAvailableBalance();
		BalancePanel.add(availableBalance);
		return BalancePanel;
	}

	/**
	 * Panel contained by the Betting panel that contains the text
	 * that is displayed by the CurrentBet.
	 * @return
	 */
	private JPanel newBidPanel() {
		JPanel BidPanel = new JPanel();
		BidPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		BidPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel CurrentBet = newCurrentBet();
		BidPanel.add(CurrentBet);
		
		return BidPanel;
	}

	/**
	 * Button that has the same functionality of submitting your bet as blackjack.
	 * Starts the game. 
	 * OnClick:
	 * 1.)Locks buttons in the bettingPanel
	 * 2.)Updates the current bet to show the amount bet.
	 * 3.)Calls the method deal
	 * 4.)Creates the cards and adds their img path.
	 * 5.)Evaluates if natural blackjack or not, proceeds accordingly.
	 * @return
	 */
	private JButton newbtnSubmit() {
		btnSubmit = new JButton("Submit");
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Locks the buttons
				btnSubmit.setEnabled(false);
				btnDecreaseBet.setEnabled(false);
				btnIncreaseBet.setEnabled(false);
				
				//displays the following: Bet Submitted: {bet}
				currentBet.setText("Bet Submitted: "+gameState.getCurrentBet());
				
				//call deal method to get cards
			    cards = gameState.deal();
			
				//Create the dealers cards
				//need to create all of the cards to be able to have them stack correctly. 
				JLabel dcard1 = newDealerCard1("");
				dealersCardsPane.add(dcard1);
				
				JLabel dcard2 = newDealerCard2("");
				dealersCardsPane.add(dcard2);
				
				JLabel dcard3 = newDealerCard3("");
				dealersCardsPane.add(dcard3);
				
				JLabel dcard4 = newDealerCard4("");
				dealersCardsPane.add(dcard4);
				
				JLabel dcard5 = newDealerCard5("");
				dealersCardsPane.add(dcard5);
				
				JLabel dcard6 = newDealerCard6("");
				dealersCardsPane.add(dcard6);
				
				JLabel dcard7 = newDealerCard7(cards.get(1).toString());
				dealersCardsPane.add(dcard7);
				
				JLabel dcard8 = newDealerCard8("/img/1_back_of_card.jpg");
				dealersCardsPane.add(dcard8);
				
				//Create the playersCards 
				JLabel pcard1 = newPlayerCard1("");
				playersCardsPane.add(pcard1);
				
				JLabel pcard2 = newPlayerCard2("");
				playersCardsPane.add(pcard2);
			
				JLabel pcard3 = newPlayerCard3("");
				playersCardsPane.add(pcard3);
				
				JLabel pcard4 = newPlayerCard4("");
				playersCardsPane.add(pcard4);
				
				JLabel pcard5 = newPlayerCard5("");
				playersCardsPane.add(pcard5);
				
				JLabel pcard6 = newPlayerCard6("");
				playersCardsPane.add(pcard6);
				
				JLabel pcard7 = newPlayerCard7(cards.get(3).toString());
				playersCardsPane.add(pcard7);
				
				JLabel pcard8 = newPlayerCard8((cards.get(2).toString()));
				playersCardsPane.add(pcard8);

				//checks for natural blackjack
				if(gameState.getPlayerScore() == 21) {
					gameState.naturalBlackjack();
					
					//displays game outcome
					lblGameOutcome.setText(gameState.getGameOutcome());
					
					//flips card
					dealerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cards.get(0).toString())));
					
					//displays the updated balance
					availableBalance.setText("Balance: " + gameState.getBankroll());

				}else {
					//enable hit and stay btn
					btnHit.setEnabled(true);
					btnStay.setEnabled(true);
				}
			}
		});
		btnSubmit.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnSubmit;
	}
	
	/**
	 * Creates a layered pane that all of the cards for the players are displayed on.
	 * 
	 * I originally created two separate JPanel classes, but I was running into issues.
	 * I needed to add cards one at a time (assigning a new value to the image path), but I couldn't
	 * figure out a way to send any variables to a Jframe. Tried making a setter, tried
	 * sending it to the constructor, but nothing I tried I could get to work. Researching after I finished this approach
	 * I found a thread talking about this, and someone said that you can send a string via a getter 
	 * that has the functionality of a setter in the Jframe. I would love input on this. 
	 */
	private JPanel newPlayersCards() {
		playersCards = new JPanel();
		playersCards.setBackground(new Color(0, 100, 0));
		playersCards.setBounds(320, 256, 335, 182);
		
		newPlayersCardsPane();
		playersCards.setLayout(new BorderLayout(0, 0));
		playersCards.add(playersCardsPane);
		return playersCards;
	}
	
	/**
	 * Creates a layered pane that all of the cards for the dealer are displayed on. 
	 */
	private JPanel newDealersCards() {
		dealersCards = new JPanel();
		dealersCards.setBackground(new Color(0, 100, 0));
		dealersCards.setBounds(320, 11, 335, 182);
		
		newDealersCardsPane();
		dealersCards.setLayout(new BorderLayout(0, 0));
		dealersCards.add(dealersCardsPane);
		
		return dealersCards;
	}
	
	/**
	 * Creates a single card that is added to the PlayersCards/DealersCards panel
	 */
	private void newPlayersCardsPane() {
		playersCardsPane = new JLayeredPane();
	}

	/**
	 * Creates a single card that is added to the PlayersCards/DealersCards panel
	 */
	private void newDealersCardsPane() {
		dealersCardsPane = new JLayeredPane();
	}
	
	private JLabel newPlayerCard8(String cardPath) {
		playerCard8 = new JLabel("");
		playerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard8.setBounds(0, 0, 125, 182);
		return playerCard8;
	}

	private JLabel newPlayerCard7(String cardPath) {
		playerCard7 = new JLabel("");
		playerCard7.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard7.setBounds(30, 0, 125, 182);
		return playerCard7;
	}

	private JLabel newPlayerCard6(String cardPath) {
		playerCard6 = new JLabel("");
		playerCard6.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard6.setBounds(60, 0, 125, 182);
		return playerCard6;
	}

	private JLabel newPlayerCard5(String cardPath) {
		playerCard5 = new JLabel("");
		playerCard5.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard5.setBounds(90, 0, 125, 182);
		return playerCard5;
	}

	private JLabel newPlayerCard4(String cardPath) {
		playerCard4 = new JLabel("");
		playerCard4.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard4.setBounds(120, 0, 125, 182);
		return playerCard4;
	}

	private JLabel newPlayerCard3(String cardPath) {
		playerCard3 = new JLabel("");
		playerCard3.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard3.setBounds(150, 0, 125, 182);
		return playerCard3;
	}
	
	private JLabel newPlayerCard2(String cardPath) {
		playerCard2 = new JLabel("");
		playerCard2.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard2.setBounds(180, 0, 125, 182);
		return playerCard2;
	}
	
	private JLabel newPlayerCard1(String cardPath) {
		playerCard1 = new JLabel("");
		playerCard1.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		playerCard1.setBounds(210, 0, 125, 182);
		return playerCard1;
	}

	private JLabel newDealerCard8(String cardPath) {
		dealerCard8 = new JLabel("");
		dealerCard8.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard8.setBounds(0, 0, 125, 182);
		return dealerCard8;
	}

	private JLabel newDealerCard7(String cardPath) {
		dealerCard7 = new JLabel("");
		dealerCard7.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard7.setBounds(30, 0, 125, 182);
		return dealerCard7;
	}

	private JLabel newDealerCard6(String cardPath) {
		dealerCard6 = new JLabel("");
		dealerCard6.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard6.setBounds(60, 0, 125, 182);
		return dealerCard6;
	}

	private JLabel newDealerCard5(String cardPath) {
		dealerCard5 = new JLabel("");
		dealerCard5.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard5.setBounds(90, 0, 125, 182);
		return dealerCard5;
	}

	private JLabel newDealerCard4(String cardPath) {
		dealerCard4 = new JLabel("");
		dealerCard4.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard4.setBounds(120, 0, 125, 182);
		return dealerCard4;
	}

	private JLabel newDealerCard3(String cardPath) {
		dealerCard3 = new JLabel("");
		dealerCard3.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard3.setBounds(150, 0, 125, 182);
		return dealerCard3;
	}
	
	private JLabel newDealerCard2(String cardPath) {
		dealerCard2 = new JLabel("");
		dealerCard2.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard2.setBounds(180, 0, 125, 182);
		return dealerCard2;
	}
	
	private JLabel newDealerCard1(String cardPath) {
		dealerCard1 = new JLabel("");
		dealerCard1.setIcon(new ImageIcon(BlackjackGUI.class.getResource(cardPath)));
		dealerCard1.setBounds(210, 0, 125, 182);
		return dealerCard1;
	}
}
