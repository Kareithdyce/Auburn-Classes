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
#                  
# ID         -->   ALPHA+
# ALPHA      -->   a  |  b  | … | z  or 
#                  A  |  B  | … | Z
# INT        -->   DIGIT+
# DIGIT      -->   0  |  1  | …  |  9
# WHITESPACE -->   Ruby Whitespace

#
#  Class Scanner - Reads a TINY program and emits tokens
#
class Scanner 
	attr_accessor :exists
# Constructor - Is passed a file to scan and outputs a token
#               each time nextToken() is invoked.
#   @c        - A one character lookahead 
	def initialize(filename)
		# Need to modify this code so that the program
		# doesn't abend if it can't open the file but rather
		# displays an informative message
		if !File.exist?(filename)
			puts "No file found"
			@exists = false
		else
			@exists = true
			@f = File.open(filename,'r:utf-8')
			
			# Go ahead and read in the first character in the source
			# code file (if there is one) so that you can begin
			# lexing the source code file 
			if (! @f.eof?)
				@c = @f.getc()
			else
				@c = "!eof!"
				@f.close()
			end
		end
	end

	def exists?
		return @exists
	end
	# Method nextCh() returns the next character in the file
	def nextCh()
		if (! @f.eof?)
			@c = @f.getc()
		else
			@c = "!eof!"
		end
		
		return @c
	end

	# Method nextToken() reads characters in the file and returns
	# the next token
	def nextToken() 
		#Handles EOF
		if @c == "!eof!"
			return Token.new(Token::EOF,"eof")
			
		#Handles White Space
		elsif (whitespace?(@c))
			str =""
		
			while whitespace?(@c)
				str += @c
				nextCh()
			end
		
			return Token.new(Token::WS,str)
		#Handles Letters
		 elsif letter?(@c)
			str = ""
			
			while(letter?(@c))
				str += @c
				nextCh()
			end

			if numeric?(@c)
				str += @c
				nextCh()
			
				while(numeric?(@c) || letter?(@c))
					str += @c
					nextCh()
				end
				
				return Token.new("unknown",str)
			end

			if str == "print"
				return Token.new(Token::PRINT, str)
			end	
			
			return Token.new(Token::VAR,str)
		#Handles numbers	
		elsif (numeric?(@c))
			str = ""
			
			while(numeric?(@c))
				str += @c
				nextCh()
			end
			
			if letter?(@c)
				str += @c
				nextCh()

				while(letter?(@c) || numeric?(@c))
					str += @c
					nextCh()
				end
	
				return Token.new("unknown",str)
			end

			return Token.new(Token::INT,str)
		
		#Handles left parenthesis
		elsif @c =~ /^[(]$/ 
			str = "("
			nextCh()
			return Token.new(Token::LPAREN,str)

		#Handles right parenthesis
		elsif @c =~ /^[)]$/ 
			str = ")"
			nextCh()
			return Token.new(Token::RPAREN,str)

		#Handles equal
		elsif @c =~ /^[=]$/ 
			str = "="
			nextCh()
			return Token.new(Token::EQUAL,str)

		#Handles addition
		elsif @c =~ /^[+]$/ 
			str = "+"
			nextCh()
			return Token.new(Token::ADDOP,str)
		
		#Handles Subtraction	
		elsif @c =~ /^[-]$/ 
			str = "-"
			nextCh()
			return Token.new(Token::SUBOP,str)

		#Handles multiplication
		elsif @c =~ /^[*]$/ 
			str = "*"
			nextCh()
			return Token.new(Token::MULTIOP,str)
		
		#Handles division	
		elsif @c =~ /^[\/]$/ 
			str = "/"
			nextCh()
			return Token.new(Token::DIVOP,str)
			
		#Handles the rest
		else
			str = @c
			nextCh()
			return Token.new("unknown",str)
		end
	
end
#
# Helper methods for Scanner
#
def letter?(lookAhead)
	lookAhead =~ /^[a-z]|[A-Z]$/
end

def numeric?(lookAhead)
	lookAhead =~ /^(\d)+$/
end

def whitespace?(lookAhead)
	lookAhead =~ /^(\s)+$/
end
end


