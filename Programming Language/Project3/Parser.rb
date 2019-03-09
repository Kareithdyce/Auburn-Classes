# https://www.cs.rochester.edu/~brown/173/readings/05_grammars.txt
#
#  "TINY" Grammar
#
# PGM        -->   STMT+
# STMT       -->   ASSIGN   |   "print"  EXP
# ASSIGN     -->   ID  "="  EXP
# EXP        -->   TERM   ETAIL
# ETAIL      -->   "+" TERM   ETAIL  | "-" TERM   ETAIL | EPSILON
# TERM       -->   FACTOR  TTAIL
# TTAIL      -->   "*" FACTOR TTAIL  | "/" FACTOR TTAIL | EPSILON
# FACTOR     -->   "(" EXP ")" | INT | ID
# ID         -->   ALPHA+
# ALPHA      -->   a  |  b  | … | z  or
#                  A  |  B  | … | Z
# INT        -->   DIGIT+
# DIGIT      -->   0  |  1  | …  |  9
# WHITESPACE -->   Ruby Whitespace

#
#  Parser Class
#
load "./Token.rb"
load "./Lexer.rb"
class Parser < Scanner
	def initialize(filename)
    	super(filename)
    	consume()
   	end
   	
	def consume()
      	@lookahead = nextToken()
      	while(@lookahead.type == Token::WS)
        	@lookahead = nextToken()
      	end
   	end
  	
	def match(dtype)
		str = classifer(dtype)
		if (@lookahead.type != dtype)
			raise "Expected #{dtype} found #{@lookahead.type}"
		else
			puts "Found #{str} Token: #{@lookahead.get_text}"
		end
      	consume()
   	end
	   
	def mismatch(dtype)
		str = classifer(dtype)
		if (@lookahead.type != dtype)
			puts "Expecting to find #{str} Token here. Instead found: #{@lookahead.get_text}"
			@errors += 1
		else 
			raise "Types should not match"
		end
		consume()
	end
	# "Might" need to modify this. Errors?
	def program()
		@errors = 0
      	while( @lookahead.type != Token::EOF)
        	puts "Entering STMT Rule"
			statement()  
		end
		if(@errors == 0)
			puts "Program parsed with no errors."
		else
			puts "There were #{@errors} parse errors found."
		end

   	end

	def statement()
		if (@lookahead.type == Token::PRINT)
			puts "Found PRINT Token: #{@lookahead.text}"
			match(Token::PRINT)
			exp()
		else
			assign()
		end
		
		puts "Exiting STMT Rule"
	end

	def assign()
		puts "Entering ASSGN Rule"
		id()
		if(@lookahead.type == Token::ASSGN)
			match(Token::ASSGN)
		else
			mismatch(Token::ASSGN)
		end
		exp()
		puts "Exiting ASSGN Rule"
	end

	def id()
		puts "Entering ID Rule"
		if(@lookahead.type == Token::ID)		
			match(Token::ID)
		else
			mismatch(Token::ID)
		end
			puts "Exiting ID Rule"
	end

	def exp()
		puts "Entering EXP Rule"
		term()
		etail()
		puts "Exiting EXP Rule"	
	end

	def term()
		puts "Entering TERM Rule"
		factor()
		ttail()
		puts "Exiting TERM Rule"
	end

	def etail()
		puts "Entering ETAIL Rule"
		if(@lookahead.type == Token::ADDOP)
			match(Token::ADDOP)
			term()
			etail()
		elsif(@lookahead.type == Token::SUBOP)
			match(Token::SUBOP)
			term()
			etail()
		else
		puts "Did not find ADDOP or SUBOP Token, choosing EPSILON production"
		end
		puts "Exiting ETAIL Rule"
	end

	def ttail()
		puts "Entering TTAIL Rule"
		if(@lookahead.type == Token::DIVOP)
			match(Token::DIVOP)
			factor()
			ttail()
		elsif(@lookahead.type == Token::MULTOP)
			match(Token::MULTOP)
			factor()
			ttail()
		else
		puts "Did not find MULTOP or DIVOP Token, choosing EPSILON production"
		end
		puts "Exiting TTAIL Rule"
	end

	def factor()
		puts "Entering FACTOR Rule"
		if(@lookahead.type == Token::LPAREN)
			match(Token::LPAREN)
			exp()
			match(Token::RPAREN)	
		elsif(@lookahead.type == Token::INT)
			match(Token::INT)
		elsif(@lookahead.type == Token::ID)
			match(Token::ID)
		else
			puts "Expecting to see ( or INT or ID Token. Instead found #{@lookahead.get_text}"
			@errors += 1
			consume()
		end
		puts "Exiting FACTOR Rule"
	end

	def classifer(tok)
		case tok
			when '('
				return "LPAREN"
			when ')'
				return "RPAREN" 
			when '+'
				return "ADDOP"
			when '-'
				return"SUBOP"
			when '/'
				return "DIVOP"   
			when '*'
				return "MULTOP" 
			when '='
				return "ASSGN"       
			when 'id'
				return "ID"       
			when 'int'
				return "INT"       
			when 'print'
				return "PRINT"       
			else
				return "UNKNWN"
			end
  	end

end
