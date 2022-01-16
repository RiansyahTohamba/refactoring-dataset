# belajar maxnesting
# try -catch : dihitungkah ?
# contohnya saveProjectProperties()
# bagaimana dengan while?
require 'sqlite3'
def info_table(table)
	db = SQLite3::Database.new "../sqlite-jcodeodor/omegat-compiled.sqlite"
    db.results_as_hash = true
	db.execute("PRAGMA table_info(#{table})") do |row|
		p row		  	
	end	
end
begin       
    # info_table('MethodsAndTypes')
    # info_table('Measurables')
    # info_table('Measures')
    db = SQLite3::Database.new "../sqlite-jcodeodor/omegat-compiled.sqlite"
    db.results_as_hash = true
	db.execute("SELECT * FROM Measurables WHERE id IN (982,1175,1316)") do |row|
		p row		  	
	end
# {"id"=>1007, "type"=>"method", "name"=>"saveProjectProperties()", "parent"=>982,NotLoadedProject}
# {"id"=>1176, "type"=>"method", "name"=>"saveProjectProperties()", "parent"=>1175,IProject}
# {"id"=>1318, "type"=>"method", "name"=>"saveProjectProperties()", "parent"=>1316,(kelas RealProject)}

	db.execute("select ms.id,key,me.type,value from Measurables as ms left join Measures as me 
on ms.id = me.measurable
		WHERE ms.id IN ('1007','1176','1318') AND key = 'MAXNESTING'") do |row|
		p row		  	
	end
# {"id"=>1007, "key"=>"MAXNESTING", "type"=>"metric", "value"=>1.0}
# {"id"=>1176, "key"=>"MAXNESTING", "type"=>"metric", "value"=>0.0}
# {"id"=>1318, "key"=>"MAXNESTING", "type"=>"metric", "value"=>2.0}

# try-finally dianggap 2

rescue SQLite3::Exception => e     
    puts "Exception occurred"
    puts e    
ensure
    db.close if db
end

