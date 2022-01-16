# delta_nom.rb
require 'sqlite3'

class Measurement
	def initialize(project)
		@db_path = "dch-jcodeodor/#{project}/sqlite-jcodeodor/database"
	end
	
	# db_sqlite berisi (before || after)
	def get_nom(db_sqlite)
		db = SQLite3::Database.new "#{@db_path}/#{db_sqlite}.sqlite"		
		db.results_as_hash = true
		query = 'SELECT count(*) as nom FROM Measurables WHERE type = "method"'
		nom = db.execute(query) 
		nom[0]['nom']
	end


	def number_of_methods(before, after)
		p '==== Number of Methods (NOM) ======'		
		nom_before = count_methods("#{before}")
		nom_after = count_methods("#{after}")

		p "NOM Before Refactoring: #{nom_before}"
		p "NOM After Refactoring: #{nom_after}"
		# penambahan change_nom/
		# Awal Penjualan = 100 unit untuk penjualan tahun lalu Akhir Penjualan = 200 unit untuk penjualan tahun ini
		# Berikut cara menghitung persentase kenaikan dengan rumus:
		# Persentase Kenaikan (%) = ((Akhir-Awal))/Awal x 100
		# Persentase Kenaikan (%) = ((200-100))/100x100%
		selisih_nom = nom_after - nom_before
		increase_percent = (selisih_nom.to_f / nom_before.to_f) * 100
		p "presentasi kenaikan NOM : #{increase_percent} %"
		# 2.2% + 
		p "=================================="
	end


end

# DeltaRefactoring.new.number_of_class(before,after)
# DeltaRefactoring.new.desc_class

# LOC dihitung dengan bantuan codemr 
# jika menggunakann jcodeodor, LOC nya ngaco

# nama db

# omegat/sqlite-jcodeodor/database/
omegat_db_before = '1-compiled'
omegat_db_after = '4-eksp'

# argouml/sqlite-jcodeodor/database/
argouml_db_before = '1-tahap-0-argouml'
argouml_db_after = '2-tahap-1-argouml'

# drjava/sqlite-jcodeodor/database/
drjava_db_before = '1-tahap-0-drjava'
drjava_db_after = '2-tahap-1-drjava'

# database
projects = ['omegat','argouml','drjava']

nom_before = 0 
nom_after = 0 

projects.each do |pr|
	nom_before = nom_before + eval("Measurement.new('#{pr}').get_nom(#{pr}_db_before)")
	nom_after = nom_after + eval("Measurement.new('#{pr}').get_nom(#{pr}_db_after)")
end
p "total nom_before of 3 project  = #{nom_before}"
p "total nom_after of 3 project  = #{nom_after}"

selisih_nom = nom_after - nom_before

increase_percent = (selisih_nom.to_f / nom_before.to_f) * 100

p "presentasi kenaikan NOM : #{increase_percent} %"	

# "presentasi kenaikan NOM : 1.0039765345092326 %"

