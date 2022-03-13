from random import choices

'''
Fabio Leandro Lapuinka

lapuinkafabio@gmail.com
03 March 2022
base64 is the key

12.03.2022 - Code is fun with the right people
Make code, dont war.
'''
TOTAL_TIROS = 3
TOTAL_CEREBROS = 13
GAME_OVER = False
'''
Objeto que presenta um player.
Representa um player'''
class Player(object):
	def __init__(self, number):
		self.number = number
		self.cerebros = 0
		self.tiros = 0
		self.jogada_atual = 0
		self.passos = 0
		self.win=False
		self.lose=False
		self.rodadas=0
		self.gameover=False
		self.exit=False
		self.dado=''
		self.face=''
		self.tubo_total_dados=13
	def  status(self):
		print("[player" + str(self.number) + "] - cerebros[" + str(self.cerebros) + "],tiros[" + str(self.tiros) + "],passos: [" + str(self.passos) + "]")   
	def play(self):
		self.dado = choices(["verde", "amarelo", "vermelho"])[0]
		if (str(self.dado) == "verde"):
			self.face = choices(['cerebro', 'tiro', 'passos'], [4, 1, 1], k=6)[0]
		if (str(self.dado) == "amarelo"):
			self.face = choices(['cerebro', 'tiro', 'passos'], [2, 2, 2], k=6)[0]
		if (str(self.dado) == "vermelho"):
			self.face = choices(['cerebro', 'tiro', 'passos'], [1, 4, 1], k=6)[0]
		print("[player" + str(self.number) + "] - "+ str(self.dado)+" : " + self.face)
		if(self.face=="cerebro"):
			self.cerebros = self.cerebros + 1
		if(self.face=="tiro"):
			self.tiros = self.tiros + 1 
		if(self.face=="passos"):
			self.passos = self.passos + 1

        
players = []

tubo_total_dados = 999

while True:
	try:
		total_jogadores = int(input("[input] - Por favor digite a qtd de jogadores 1,4: "))
		if 1 <= total_jogadores <= 4:
			break
		else:
			print("[error] - o numero deve estar entre 1 e 5: ")
	except ValueError:
		print("valor invalido")
		
print("[status ]  -  Iniciando o jogo")
for i in range(total_jogadores):
	players.append(Player(i+1))

while True:
	for player in players:
		continua=1
		'''Funcao para mostrar o status do jogador atual'''
		while(continua==1 and not player.exit):
			print("[player" + str(player.number) + "] - pressione uma tecla para lancar 3 dados...")
			input()
			if (tubo_total_dados > 3):
				print("[computer] - pegando 3 dados aleatoriamente e lancando automaticamente...")
				tubo_total_dados = tubo_total_dados - 3
			else:
				print("[computer] acabaram os dados do tubo")
				break
				
			'''joga 3 dadis por turno'''
			for i in range(3):
				player.play()
			''' 
			Regras Basica de controle do jogo, ficam no jogo e nao no player
			'''
			if(int(player.cerebros)>=int(TOTAL_CEREBROS)):
				print("[player" + str(player.number) + "] - vitoria, vc comeu >="+str(TOTAL_CEREBROS)+" cerebros!")
				player.win=True
				player.gameover=True
				player.exit=True
				GAME_OVER=True
				player.status()
				break
			if(int(player.tiros)>=int(TOTAL_TIROS)):
				print("[player" + str(player.number) + "] -  falha, vc levou >=" +str(TOTAL_TIROS)+ " tiros.!")
				player.lose=True
				player.exit=True
				player.status()
				break
				
			if(continua==0 or player.exit):
				player.status()
				print("[player" + str(player.number) + "] -- Turno encerrado")
				break
			else:
				player.status()
				print("[computer] - Deseja continuar seu turno? (1/0):")
				continua = int(input())
				player.rodadas = player.rodadas +  1
			
	print("[computer] - GAME OVER???")		
	total_players = 0
	total_exit= 0
	total_winner=0
	total_loser=0
	winner = '(none)'
	for player in players:
		if(player.win):
			GAME_OVER = True
			total_winner = total_winner + 1
			winner = player.number
		elif(player.lose):
			total_loser = total_loser + 1
		elif(player.exit):
			total_exit = total_exit + 1
		total_players = total_players + 1
		
	print("[computer] - players:" + str(total_players))
	print("[computer] - winner:" + str(total_winner) + ' player'+str(winner))
	print("[computer] - losers:" + str(total_loser))
	print("[computer] - exit:" + str(total_exit))
	if(total_players==total_exit  or total_players==total_loser):
		GAME_OVER = True
	if(GAME_OVER):
		break
	
	
print("[computer] - Fim do Jogo para todos o jogadores")

for player in players:
	player.status()
