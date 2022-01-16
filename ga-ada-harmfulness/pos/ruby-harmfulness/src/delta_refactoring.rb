require 'sqlite3'
class DeltaRefactoring
	def db_path
		 '../../sqlite-jcodeodor'
	end

	def count_methods(db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}"		
		db.results_as_hash = true
		query = 'SELECT count(*) FROM Measurables WHERE type = "method"'
		db.execute(query) do |row|
			p row
		end
	end
	def count_class(db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}"		
		db.results_as_hash = true
		query = 'SELECT count(*) FROM Measurables WHERE type <> "method" AND type <> "package" AND type <> "project" AND name NOT LIKE "org.omegat%"'
		db.execute(query) do |row|
			p row
		end
	end

	def number_of_methods(before,after)
		p '== Number of Methods ==='		
		p "BeforeRefactoring"
		count_methods("#{before}.sqlite")
		p "AfterRefactoring"
		count_methods("#{after}.sqlite")
	end

	def number_of_class(before,after)
		p '== Number of Class ==='
		p "BeforeRefactoring"
		count_class("#{before}.sqlite")
		p "AfterRefactoring"
		count_class("#{after}.sqlite")
	end

	def query_desc(db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}"		
		db.results_as_hash = true
		query = 'SELECT * FROM Measurables WHERE type <> "method" AND type <> "package" AND type <> "project" AND name NOT LIKE "org.omegat%"'
		db.execute(query) do |row|
			p row
		end
	end

	def desc_class
		query_desc('omegat-after-refactoring.sqlite')
		count_class("omegat-after-refactoring.sqlite")		
	end
end
# nama db
# omegat-before-refactoring
# omegat-after-refactoring
before = 'omegat-before-refactoring'
after = 'tahapsatu'
DeltaRefactoring.new.number_of_methods(before,after)
DeltaRefactoring.new.number_of_class(before,after)
# DeltaRefactoring.new.desc_class

# LOC dihitung dengan bantuan codemr 
# jika menggunakann jcodeodor, LOC nya ngaco
