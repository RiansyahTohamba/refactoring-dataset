# algoritma koleksi data trial refactoring
# untuk menyaring data yang akan dijadikan target refactoring.
# data sebelumnya di filter lagi.

def add_data_trial
	data_trial = []
	if dch.level == 'harm' or dch.level == 'problem'  
		unless is_configuration_function(dch.code)
			data_trial.add(dch)	
		end

	end

	while length(data_trial) <= jumlah_ideal
		data_trial.add(dari_trouble)
	else

	# length(data_trial) ?
	# sebelum filter terdapat 31 kode terdampak
	# setelah filter berapa data tersisa ? 
end
	
def jumlah_ideal
	# idealnya brp ?
	# badri = ?
end

def is_configuration_function
	if code.contain("var = new Class()") or
	   code.contain("GUI.init")
	   true
	else
		false
	end
	# GUI.init()
end


