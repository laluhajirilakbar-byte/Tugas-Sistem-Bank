import java.util.ArrayList;

class Akun {
    private final String noRekening;
    private int balance;
    private final ArrayList<String> riwayatTransaksi; 

    public Akun(String noRekening, int balance) {
        this.noRekening = noRekening;
        this.balance = balance;
        this.riwayatTransaksi = new ArrayList<>(); 
        this.riwayatTransaksi.add("Saldo Awal: Rp " + balance);
    }

    public void deposit(int amount) {
        this.balance += amount;
        this.riwayatTransaksi.add("Deposit : +Rp " + amount + " | Saldo: Rp " + this.balance);
    }

    public void withdraw(int amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            this.riwayatTransaksi.add("Withdraw: -Rp " + amount + " | Saldo: Rp " + this.balance);
        } else {
            this.riwayatTransaksi.add("Withdraw: -Rp " + amount + " (GAGAL: Saldo tidak cukup)");
        }
    }

    public int getBalance() {
        return this.balance;
    }

    public String getNoRekening() {
        return this.noRekening;
    }

    public void cetakRiwayat() {
        System.out.println("Riwayat Transaksi Rekening [" + noRekening + "]:");
        for (String transaksi : riwayatTransaksi) {
            System.out.println("   - " + transaksi);
        }
    }
}

class Nasabah {
    private final String nama;
    private final ArrayList<Akun> daftarAkun; 

    public Nasabah(String nama) {
        this.nama = nama;
        this.daftarAkun = new ArrayList<>(); 
    }

    public void bukaAkun(Akun akunBaru) {
        this.daftarAkun.add(akunBaru);
    }

    public Akun getAkun(int index) {
        return this.daftarAkun.get(index);
    }

    public String getNama() {
        return this.nama;
    }

    public void cetakInfoNasabah() {
        System.out.println("Nama Nasabah : " + this.nama);
        System.out.println("Jumlah Akun  : " + daftarAkun.size());
        for (int i = 0; i < daftarAkun.size(); i++) {
            System.out.println("  " + (i + 1) + ". No Rekening: " + daftarAkun.get(i).getNoRekening() + 
                               " | Saldo: Rp " + daftarAkun.get(i).getBalance());
        }
    }
}

class Bank {
    private final String namaBank;
    private final ArrayList<Nasabah> daftarNasabah; 

    public Bank(String namaBank) {
        this.namaBank = namaBank;
        this.daftarNasabah = new ArrayList<>(); 
    }

    public void tambahNasabah(Nasabah nasabahBaru) {
        this.daftarNasabah.add(nasabahBaru);
    }

    public Nasabah getNasabah(int index) {
        return this.daftarNasabah.get(index);
    }
 
    public void cetakDataBank() {
        System.out.println("==========================================");
        System.out.println("     DATA KESELURUHAN " + this.namaBank.toUpperCase());
        System.out.println("==========================================");
        for (Nasabah n : daftarNasabah) {
            n.cetakInfoNasabah();
            System.out.println("------------------------------------------");
        }
    }
}


public class SistemBank {
    public static void main(String[] args) {
        System.out.println("=== SELAMAT DATANG DI SISTEM BANK ===\n");

        Bank bankPusat = new Bank("Bank Mandiri Jaya");

        Nasabah nasabah1 = new Nasabah("Budi Santoso");
        Nasabah nasabah2 = new Nasabah("Siti Aminah");

        bankPusat.tambahNasabah(nasabah1);
        bankPusat.tambahNasabah(nasabah2);

        nasabah1.bukaAkun(new Akun("111-001", 100000)); 
        nasabah1.bukaAkun(new Akun("111-002", 500000)); 

        nasabah2.bukaAkun(new Akun("222-001", 250000)); 
        
        System.out.println(">> TRANSAKSI BUDI (AKUN BISNIS / Indeks 1) <<");
        Akun akunBisnisBudi = bankPusat.getNasabah(0).getAkun(1);
        
        akunBisnisBudi.deposit(600000);
        akunBisnisBudi.withdraw(150000);
        akunBisnisBudi.cetakRiwayat();
        
        System.out.println("\n>> TRANSAKSI SITI (AKUN 1 / Indeks 0) <<");
        Akun akunSiti = bankPusat.getNasabah(1).getAkun(0);
        
        akunSiti.deposit(50000);
        akunSiti.withdraw(1000000); 
        akunSiti.cetakRiwayat();

        System.out.println("\n");
        
        bankPusat.cetakDataBank();
    }
}