#Viikkoraportti, viikko2

###Mitä olen tehnyt tällä viikolla?
Yksinkertainen käyttöliittymä luotu. Tiedoston luku byte arrayhyn ja stringiin toteutettu. Myöskin FileManager luokan testausta lisätty.
Ohjelma osaa lukea tällä hetkellä mitä tahansa tiedostomuotoja ja laskea tiedoston koon oikein.

###Miten ohjelma on edistynyt?
Käyttöliittymä alkaa olla tarpeellisella tasolla itse tiedoston pakkauksen toteutusta varten.

###Mitä opin tällä viikolla / tänään?
Charseteista todella paljon uutta asiaa. Myöskin bittien käsittelyä sekä tiedostojen lukua bittistreamina javalla. Myöskin tajusin/opin huffman koodauksen perusteet, sekä sen että merkistö kannattaa muodostaa suoraan tavuista byte arrayssa eikä muuntaa Stringiksi kuten useissa esimerkeissä tehdään. Stringit toimivat tekstitiedostojen kanssa loistavasti, mutta kun halutaan pakata mikä tahansa tiedostomuoto, niin merkistön tulisi kattaa kaikki bittikombinaatiot 8 bitillä. Tällöin tulee helpommaksi vain käyttää tavuja suoraan merkistönä.

###Mikä jäi epäselväksi tai tuottanut vaikeuksia?
Minkä tahansa byte arrayn muunto Stringiksi. En löytänyt charsettia joka kattaisi kaikki bittikombinaatiot 8 bitillä.

###Mitä teen seuraavaksi?
Aloitan Huffmanin algoritmin toteutuksen kunnolla muodostamalla puun byte arraysta ja laskemalla uuden merkistön pakkausta varten.
