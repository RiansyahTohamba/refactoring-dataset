require 'sqlite3'
class Main

	def db_path
		 '../../sqlite-jcodeodor'
	end

	def exp_pendahuluan
		'tahapsatu.sqlite'				
	end
	
	def method_show_detail_query
		db = SQLite3::Database.new "#{db_path}/omegat-after-refactoring.sqlite"
	    db.results_as_hash = true
		sql = "
		   SELECT type.id as type_id, meth.id as meth_id, type.name as type, meth.name as method 
		   FROM
			   ( SELECT id,parent,name FROM Measurables WHERE name LIKE 'show(%'
			   ) as meth			   
		   inner JOIN 
			   (SELECT id,name FROM Measurables WHERE type ='type' AND
					name IN ('AlignFilePickerController','AlignPanelController',
					'BrowseTaasCollectionsController','PreferencesWindowController')					
			   ) as type
		   ON meth.parent = type.id
			"
	end

	def disp_id_omegat_compiled
		'("8220","8216","8328","5791","3156","3096","3098","7858","9554","5245","5304","5331","5354","5325","5359","5439","5443","895","4448","2958","2942","1800","3952","4395","9294","6808","6865","6878","6871","6877","2830","2836","2885","2261","8897","8898","8896","8893","7342","7351","7352","7355","7361","7362","7363","3883","1666","6705","6704","9386","1162","7249","7251","7237","7234","7242","1339","1332","1334","1342","1403","1395","7065","4893","4516","4503","4514","5071","6341","6385","5819","3131","6924","6586","8383","3127","7324")'
	end

	def disp_id_branch_refact
		# and main dari kelas AlignFilePickerController 
		# {"meth_id"=>2956, "type_id"=>2952, "name"=>"main(String[], java.lang.Exception)"},
		method_duplicate_id = "'2956','2882','6856','8331','8449'"		
		# pada non duplicate jg masih ditemukan nama method yg sama 
		# tapi duplikat jg bisa dikarenakan method tersebut di extrack ke kelas yang lain
		non_dup_method_id = "'309','1176','1330','1377','1540','1544','1549','2080','2323','2820','2823','3990','4004','4473','4530','4546','4548','4658','4660','4795','4806','4807','4808','4814','4839','4923','5104','5215','5226','5227','5228','5234','5344','5371','5377','5400','5401','5402','5407','5477','5491','6601','6609','6616','6649','6691','6863','6936','7028','7035','7042','7045','7092','7565','7624','7631','7653','7658','7659','7667','7677','7678','7680','7785','8947','9014','9551','9697','9708','9712'"
		"(#{method_duplicate_id},#{non_dup_method_id})"
	end

	def non_duplicate_method
		disp_methods = ['initializeGUI(%','doSearch(%','SearchWindowController(%','doReplace(%','mergeTMX(%','doExternalCommand(%','loadSourceFiles(%','saveProject(%','projectExitMenuItemActionPerformed(%','createNodeTree(%','createActiveSegmentElement(%','setFoundResult(%','GlossaryTextArea(%','showCreateGlossaryEntryDialog(%','populatePaneMenu(%','calcPerFile(%','forFile(%','activateEntry(%','gotoEntry(%','showStat(%','replaceEditText(%','EditorController(%','SegmentPropertiesArea(%','projectCreateMED(%','projectEditProperties(%','projectOpen(%','projectImportFiles(%','doWikiImport(%','EntryListPane(%','createMenuBar(%','install(%','DictionariesTextArea(%']
		where_meth = ''
		disp_methods.each do |meth|  
			if !meth.eql?('DictionariesTextArea(%')
				where_meth = where_meth + "name LIKE '#{meth}' OR "
			else
				where_meth = where_meth + "name LIKE '#{meth}'"
			end
		end	
		where_meth	
		# p where_meth
	end


	def cls_method_after_refactoring
		# cari ID dulu baru kita bisa cari nilai metricnya dengan 
		# method disp_id_refactoring
		db = SQLite3::Database.new "#{db_path}/omegat-after-refactoring.sqlite"
	    db.results_as_hash = true
		# sql = "SELECT id,parent,name FROM Measurables WHERE #{non_duplicate_method}"		
		sql = "SELECT id FROM Measurables WHERE #{non_duplicate_method}"		
		p sql
		result = "ID,KODE PARENT,NAME\n"
		db.execute(sql) do |row|
			# replace_arg = row['name'].gsub(/\(.*\)/, '')
		  	# result = result +"#{row['id']}, #{row['parent']}, #{replace_arg} \n"		  	
		  	result = result +"'#{row['id']}',"		  	
		end
		File.open("id_cls_method_after_refactoring.txt", "w") { |f| f.write result }		
		# File.open("cls_method_after_refactoring.txt", "w") { |f| f.write result }		
	end

	def count_after_refac
		"SELECT 
			measurable,
			MAX(CASE WHEN key== 'CINT' THEN value END) as cint,
			MAX(CASE WHEN key== 'CDISP' THEN value END) as cdisp,
			MAX(CASE WHEN key== 'MAXNESTING' THEN value END) as maxnesting,
			MAX(CASE WHEN key== 'DispersedCouplingHarmfulness' THEN value END) as harmfulness		
			FROM Measures 
			WHERE key IN ('CINT','CDISP', 'MAXNESTING','DispersedCouplingHarmfulness') 
			AND measurable IN "+disp_id_branch_refact+" GROUP BY measurable"
	end

	def query_count_before_refac
	    # DispersedCouplingHarmfulness
		"SELECT 
			measurable,
			MAX(CASE WHEN key== 'CINT' THEN value END) as cint,
			MAX(CASE WHEN key== 'CDISP' THEN value END) as cdisp,
			MAX(CASE WHEN key== 'MAXNESTING' THEN value END) as maxnesting,
			MAX(CASE WHEN key== 'DispersedCouplingHarmfulness' THEN value END) as harmfulness
			FROM Measures 
			WHERE key IN ('CINT','CDISP', 'MAXNESTING','DispersedCouplingHarmfulness') 
			AND measurable IN "+disp_id_omegat_compiled+" GROUP BY measurable
			"	
	end

	# measurable terdapat nama dan parent class nya
	def query_cls_met(query_type,order_by)
	   "SELECT type.name as type,meth.name as method,cint,cdisp,maxnesting,harmfulness
	   FROM ("+query_type+") as rescount
	   LEFT JOIN 
	   (SELECT id,parent,name FROM Measurables) as meth
	   ON meth.id=rescount.measurable
	   LEFT JOIN 
	   (SELECT id,name FROM Measurables WHERE type ='type') as type
		ON meth.parent = type.id
		ORDER BY #{order_by}
	   "
	end

	def cleaning_method_refac(row,meth_without_arg)
	  	# "#{meth_without_arg} & compiled_branch & #{row['type']} & #{row['cint']} & #{row['cdisp']} & #{row['maxnesting']} ?? \n"		
		if (/^I\w+$/ =~ row['type']).nil?
		  	# "#{meth_without_arg} & compiled_branch & #{row['type']} & #{row['cint']} & #{row['cdisp']} & #{row['maxnesting']} ?? \n"		
		  	# pakai harmfulness
		  	"#{meth_without_arg} & #{row['type']} & compiled_branch_val & #{row['harmfulness']} ?? \n"		
		else
			# utk 37 kelas ini sudah dipastikan tak ada kelas yg diawali I kecuali itu interface
			# jadi kt comment sj kode ini
		  	# "IsInterface? #{meth_without_arg} & compiled_branch & #{row['type']} & #{row['cint']} & #{row['cdisp']} & #{row['maxnesting']} ?? \n"					
		  	""
		end
	end

	def exec_db(query_exec,output_file_str,db_sqlite)
		db = SQLite3::Database.new "#{db_path}/#{db_sqlite}"
	    db.results_as_hash = true
		counter=0;	
		result = "Level & Kelas & Method & CINT & CDISP  & MAXNESTING | CINT & CDISP  & MAXNESTING\n"
		db.execute(query_exec) do |row|
			meth_without_arg = row['method'].gsub(/\(.*\)/, '')
			if db_sqlite.eql?('omegat-after-refactoring.sqlite')
				result = result + cleaning_method_refac(row,meth_without_arg)
			else
				# pakai harmfulness
			  	# result = result +"#{row['harmfulness']} & #{row['type']} & #{meth_without_arg} & #{row['cint']} & #{row['cdisp']} & #{row['maxnesting']} & 0 & 0 & 0 \n"
			  	result = result + "#{meth_without_arg} & #{row['type']} & #{row['cint']} & #{row['cdisp']} & #{row['maxnesting']} \n"
			end
		end
		File.open(output_file_str, "w") { |f| f.write result }
		p "#{output_file_str} has generated"
	end

	def refactoring_therest_branch
		db_sqlite = "omegat-after-refactoring.sqlite"
		p count_after_refac
		query_type = query_cls_met(count_after_refac,"type desc")
		file_name = "aft-disp-orderharmful.txt"
		exec_db(query_type,file_name,db_sqlite)
	end

	def compiled_branch
		db_sqlite = "omegat-before-refactoring.sqlite"
		query_type = query_cls_met(query_count_before_refac,"type desc")
		file_name = "comp_disp-order-type.txt"
		exec_db(query_type,file_name,db_sqlite)
	end

	def main
		# duplicate_method
		# non_duplicate_method
		# awalnya refactoring the rest harus di-cleaning dulu
		# compiled_branch
		# bagaimana cara digabungkan ?

		# compiled_branch replace dengan IsNewClass?
		# compiled_branch = #{row['type_compiled']} & #{row['cint_compiled']} & #{row['cdisp_compiled']} & #{row['maxnesting_compiled']}
		# bagaimana cara insert compiled_branch ke tengah-tengah itu		
		#{replace_arg} & compiled_branch & #{row['type']} & #{row['cint']} & #{row['cdisp']} & #{row['maxnesting']} ??
		refactoring_therest_branch
	end
end

Main.new.main
