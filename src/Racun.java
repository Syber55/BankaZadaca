import java.util.*;


public class Racun {
	private int brojRacuna;
	private String imeRacuna;
	private double novacNaRacunu;
	private static ArrayList<Racun> podaci = new ArrayList<>();
	private static final Scanner in = new Scanner(System.in);

	Racun() {
		dodajRacun(this);
	}

	Racun(int brojRacuna, String imeRacuna, double novacNaRacunu) {
		this.brojRacuna = brojRacuna;
		this.imeRacuna = imeRacuna;
		this.novacNaRacunu = novacNaRacunu;
		dodajRacun(this);
	}

	public void dodajRacun(Racun racun) {
		podaci.add(racun);
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(int brojRacuna) {
		while (provjeraBrojaRacuna(brojRacuna) || brojRacuna < 0) {
			if (provjeraBrojaRacuna(brojRacuna)) {
				System.out.println("Unijeli ste zauzet broj, molimo Vas da unesete drugi broj");
			} else if (brojRacuna < 0) {
				System.out.println("Unijeli ste negativan broj, molimo Vas da unesete drugi broj");
			}
			brojRacuna = in.nextInt();
		}
		this.brojRacuna = brojRacuna;
	}

	public String getImeRacuna() {
		return imeRacuna;
	}

	public void setImeRacuna(String imeRacuna) {
		this.imeRacuna = imeRacuna;
	}

	public double getNovacNaRacunu() {
		return novacNaRacunu;
	}

	public void setNovacNaRacunu(double novac) {
		while (novac < 0) {
			System.out.println("Unijeli ste negativnu sumu novca, molimo Vas da ponovo pokušate...");
			novac = in.nextDouble();
		}
		this.novacNaRacunu = novac;
	}

	public boolean provjeraBrojaRacuna(int broj) {
		for (int i = 0; i < podaci.size(); i++) {
			if (broj == podaci.get(i).getBrojRacuna()) {
				return true;
			}
		}
		return false;
	}

	public static void statGetter(int broj) {
		for (int i = 0; i < podaci.size(); i++) {
			if (broj == podaci.get(i).getBrojRacuna()) {
				System.out.println("Broj racuna :" + podaci.get(i).getBrojRacuna());
				System.out.println("Ime vlasnika racuna :" + podaci.get(i).getImeRacuna());
				System.out.println("Kolicina novca na racunu (u KM) :" + podaci.get(i).getNovacNaRacunu());
			}
		}
	}

	public static void transfer(int id1, int id2, double amount) {
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < podaci.size(); i++) {
			if (id1 == podaci.get(i).getBrojRacuna()) {
				index1 = i;
			}
		}
		if (index1 == -1) {
			System.out.println("Raèun sa kojeg se šalje novac ne postoji");
			return;
		}
		for (int i = 0; i < podaci.size(); i++) {
			if (id2 == podaci.get(i).getBrojRacuna()) {
				index2 = i;
			}
		}
		if (index2 == -1) {
			System.out.println("Raèun koji prima novac ne postoji");
			return;
		}
		if (podaci.get(index1).getNovacNaRacunu() < amount) {
			System.out.println("Nemate dovoljno novca na racunu.");
			return;
		}
		double suma = podaci.get(index1).getNovacNaRacunu();
		suma -= amount;
		podaci.get(index1).setNovacNaRacunu(suma);
		suma = podaci.get(index2).getNovacNaRacunu();
		suma += amount;
		podaci.get(index2).setNovacNaRacunu(suma);
		System.out.println("Transkacija uspjesna!");
		return;
	}
	public static int arrayLength() {
		return podaci.size();
	}
	public static String arrayElement(int n) {
		String out = "";
		out+=podaci.get(n).getBrojRacuna();
		out+="'";
		out+=podaci.get(n).getImeRacuna();
		out+="'";
		out+=podaci.get(n).getNovacNaRacunu();
		out+="'";
		return out;
	}
}
