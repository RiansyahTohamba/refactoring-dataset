// id , type,name, parent
// "26"    "method"    "borrow()"  "24"
public class Simulation
{
    IBook bk,bksc;
    public Simulation() {
        // FANOUT = 2
        // CDISP = 1, CINT = 2
        // CDISP = 2 / 2 = 1 
        bk = new NewBook(); 
        bksc = new ScienceBook(); 
    }

    public void showBook() {
//        CDISP = FANOUT/CINT = 2/3 = 0.666666666666667 , 
//        class yg dipanggil ScienceBook dan Author
//        CINT=3,chain dihitung 2, class dihitung 1
        ScienceBook swengineering = new ScienceBook(); 
        swengineering.getDetail().getName();
    }

    public void borrow() {
        // CINT 5, CDISP 0.2        
        // Class yg dipanggil cuman IBook jadi CDISP adalah 1/5        
        bk.find();
        bksc.find();
        if(bk.isBorrow()){
            bk.readResensi();
        }else{
            bk.borrow();
        }
        bk.sendEmail();
    }
// notes:
    // hasil hitung dari sqlite, borrow =  CINT 5, CDISP 0.2

    // CBO = 2, sumbernya cuman object bk+bksc, jadi CBO tak bisa digunakan untuk menghitung CINT
    // mungkin saja CBO kecil tapi intesity tinggi and visa versa
    // MC = 6
    // MC juga menghitung methodCall yang berasal dari method miliki class sendiri 
    // maka MC tak bisa dijadikan acuan
    // harusnya method call yang dihitung adalah method call punya kelas lain
    // kalau mpc bagaimana?
    // AF = 2

    // Parent yg sama
    // tapi dari interface yang sama(IBook) ? apakah cara hitungnya berpengaruh?
    // bk dan bksc ini dari 1 parent yg sama (IBook), jadi FANOUT = 1
    // bk dan bksc dianggap 2 Class called bukan cuman 1
    // maka find() pada kedua obj. e.g bk dan bksc,CINT nya dianggap +1 bukan +2
    // CDISP = 1/5 = 0.2
    
}