#Testausdokumentti

##JUnit Testaus
Ohjelman luokille on testy JUnit testit siinä määrin kun on nähty tarpeelliseksi ja aika on riittänyt. ByteHandler luokan writeExtractedFile ja writePackedFile metodien testit testaavat käytännössä koko ohjelmaa, sillä jos joku muu luokka/testi epäonnistuu ei ByteHandlerin testitkään mene läpi.

##Empiirinen testaus
Ohjelmaa on myöskin testattu eri formaateilla ja tiedostokooilla. Mainittakoon, että kun tietorakenteet vaihdettiin omiin lisääntyi suoritusaika huomattavasti. Luultavasti tämä johtuu siitä, että käytin Javan omaa primitiivistä arrayta omien tietorakenteideni pohjalla ja pari operaatiota vaatii listan kopioimista. Tämä johtaa siihen, että isoilla syötteillä ohjelman suoritus alkaa kestämään turhauttavan kauan. Javan ArrayListiä ja HashMappia käyttämällä suoritusaika ainakin puoliutui.

Testasin ohjelmaa esim. kahdella Harry Potterilla. Pakkaamattomana kirjat veivät 1.1 MB ja pakattuna 0.7MB. Pakkaussuhde oli siis 0.63, eli 63%. 

jpg-kuvalla testattuna pakkaus vaan kasvatti tiedostokokoa, mikä johtuu siitä, että jpg on valmiiksi pakattu formaatti ja sisältää satunnaisia tavuja. Huffman toimii parhaiten kun tietyt tavut esiintyvät paljon useammin kuin toiset, mikä on ominaista juuri tekstitiedostoille.
