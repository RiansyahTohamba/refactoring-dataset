class NewBook
	def get_bill
		# isi method
	end
end
class ScienceBook
	def get_bill
		# isi method
	end
end

class Main
	def count_total(book)
		save(book.get_bill)
	end

	def main
		scbk = ScienceBook.new(100)
		newbk = NewBook.new(10000)

		count_total(scbk)
		count_total(newbk)
	end
end
