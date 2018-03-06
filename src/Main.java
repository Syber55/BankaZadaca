import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		load();
		while (true) {
			System.out.println();
			System.out.println("Dobrodosli u banku, Odaberite jednu od opcija:");
			System.out.println("1. Kreiranje racuna");
			System.out.println("2. Prebacivanje novca sa jednog racuna na drugi");
			System.out.println("3. Ispisivanje detalja postojecih racuna");
			System.out.println("4. Izlaz");
			int ulaz = in.nextInt();
			switch (ulaz) {
			case 1:
				Racun user1 = new Racun();
				System.out.println("Unesite id broj racuna");
				user1.setBrojRacuna(in.nextInt());
				System.out.println("Unesite ime vlasnika racuna");
				user1.setImeRacuna(in.next());
				System.out.println("Unesite kolicinu novca na racunu");
				user1.setNovacNaRacunu(in.nextDouble());
				break;
			case 2:
				boolean provjera = true;
				System.out.println("Unesite ID broj racuna sa kojeg se salje novac");
				int id1 = 0;
				while (provjera) {
					try {
						id1 = in.nextInt();
						provjera = false;
					} catch (InputMismatchException ex) {
						in.next();
						System.out.println("Pokušaj unijeti broj...");
						provjera = true;
					}
				}
				System.out.println("Unesite ID broj racuna koji prima novac");
				int id2 = 0;
				provjera = true;
				while (provjera) {
					try {
						id2 = in.nextInt();
						provjera = false;
					} catch (InputMismatchException ex) {
						in.next();
						System.out.println("Pokušaj unijeti broj...");
						provjera = true;
					}
				}
				System.out.println("Unesite željenu sumu novca");
				double suma = 0;
				provjera = true;
				while (provjera) {
					try {
						suma = in.nextDouble();
						provjera = false;
					} catch (InputMismatchException ex) {
						in.next();
						System.out.println("Pokušaj unijeti broj...");
						provjera = true;
					}
				}
				Racun.transfer(id1, id2, suma);
				break;
			case 3:
				System.out.println("Unesite ID broj željenog raèuna :");
				Racun.statGetter(in.nextInt());
				break;
			case 4:
				System.out.println("Program završen!");
				in.close();
				save();
				System.exit(0);
				break;
			default:
				System.out.println("Pogriješili ste unos.");
				break;
			}
		}
	}

	public static void save() {
		File fajl = new File("fajl");
		int broj = Racun.arrayLength();
		String unos = "";
		System.out.println("Zapoèeto pisanje...");
		try (FileWriter output = new FileWriter(fajl)) {
			for (int i = 0; i < broj-1; i++) {
				unos = Racun.arrayElement(i);
				output.write(unos);
				output.write("\n");
				output.flush();
			}
			unos = Racun.arrayElement(broj-1);
			output.write(unos);
			output.flush();
			output.close();
		} catch (IOException ex) {
			System.out.println("IOException...");
			System.exit(0);
		}
		System.out.println("Pisanje uspješno završeno!");
	}

	public static void load() {
		File fajl = new File("fajl");
		int broj = 0;
		int brojRacuna = 0;
		String imeKorisnika = "";
		double novac = 0;
		String s1 = "";
		try (Scanner fileIn = new Scanner(fajl)) {
			String linija = "";
			while (fileIn.hasNext()) {
				linija = fileIn.nextLine();
				for (int i = 0; i < linija.length(); i++) {
					if (linija.charAt(i) != '\'') {
						s1 += linija.charAt(i);
					} else {
						broj = i;
						break;
					}
				}
				brojRacuna = Integer.parseInt(s1);
				s1 = "";
				for (int i = broj + 1; i < linija.length(); i++) {
					if (linija.charAt(i) != '\'') {
						s1 += linija.charAt(i);
					} else {
						broj = i;
						imeKorisnika = s1;
						s1 = "";
						break;
					}
				}
				for (int i = broj + 1; i < linija.length(); i++) {
					if (linija.charAt(i) != '\'') {
						s1 += linija.charAt(i);
					}
				}
				novac = Double.parseDouble(s1);
				s1 = "";
				Racun user = new Racun(brojRacuna, imeKorisnika, novac);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Fajl nije pronaðen");
			System.exit(0);
		} catch (IOException ex) {
			System.out.println("IOException...");
			System.exit(0);
		}
	}
}
