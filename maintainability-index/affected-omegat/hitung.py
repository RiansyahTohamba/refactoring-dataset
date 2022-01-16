import pandas as pd    
import numpy as np
import sqlite3 as sql

class Harmfulness:
    def __init__(self, db):
        self.conn = sql.connect('../dispersion-coupling-harmfulness/sqlite-jcodeodor/database/'+db)

    def dist(self): 
        return pd.read_sql('select * from mi_compiled_sample',self.conn)
    
    def print_excel(self): 
        dist = self.dist()
        return dist
    
    def get_table(self):    
        pass


comp = Harmfulness('micompiled.sqlite')
exp2 = Harmfulness('miexperiment2.sqlite')