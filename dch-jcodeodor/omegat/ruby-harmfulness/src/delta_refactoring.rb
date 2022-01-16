require 'sqlite3'
class DeltaRefactoring
	def db_path
		 '../../sqlite-jcodeodor/database'
	end

	def count_methods(db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}.sqlite"		
		db.results_as_hash = true
		query = 'SELECT count(*) as nom FROM Measurables WHERE type = "method"'
		nom = db.execute(query) 
		nom[0]['nom']
	end
	def count_class(db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}.sqlite"		
		db.results_as_hash = true
		query = 'SELECT count(*) as noc FROM Measurables WHERE type <> "method" AND type <> "package" AND type <> "project" AND name NOT LIKE "org.omegat%"'
		noc = db.execute(query) 
		noc[0]['noc']
	end

	def number_of_methods(before,after)
		p '==== Number of Methods (NOM) ======'		
		nom_before = count_methods("#{before}")
		nom_after = count_methods("#{after}")

		p "NOM Before Refactoring: #{nom_before}"
		p "NOM After Refactoring: #{nom_after}"
		selisih_nom = nom_after - nom_before
		# penambahan change_nom/
		# Awal Penjualan = 100 unit untuk penjualan tahun lalu Akhir Penjualan = 200 unit untuk penjualan tahun ini
		# Berikut cara menghitung persentase kenaikan dengan rumus:
		# Persentase Kenaikan (%) = ((Akhir-Awal))/Awal x 100
		# Persentase Kenaikan (%) = ((200-100))/100x100%
		increase_percent = (selisih_nom.to_f / nom_before.to_f) * 100
		p "presentasi kenaikan NOM : #{increase_percent} %"
		# 2.2% + 
		p "=================================="
	end

	def number_of_class(before,after)
		p '== Number of Class ==='
		p "BeforeRefactoring"
		count_class("#{before}")
		p "AfterRefactoring"
		count_class("#{after}")
		p "=================================="
	end

	def query_desc(db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}.sqlite"		
		db.results_as_hash = true
		query = 'SELECT * FROM Measurables WHERE type <> "method" AND type <> "package" AND type <> "project" AND name NOT LIKE "org.omegat%"'
		db.execute(query) do |row|
			p row
		end
	end

	def desc_class
		# query_desc('omegat-after-refactoring.sqlite')
		# count_class("omegat-after-refactoring.sqlite")		
	end
end
# nama db

before = '1-compiled'
after = '4-eksp'
DeltaRefactoring.new.number_of_methods(before,after)
# DeltaRefactoring.new.number_of_class(before,after)
# DeltaRefactoring.new.desc_class

# LOC dihitung dengan bantuan codemr 
# jika menggunakann jcodeodor, LOC nya ngaco
