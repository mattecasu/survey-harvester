
// Convert to Roman Numerals
// copyright 25th July 2005, by Stephen Chapman http://javascript.about.com
// permission to use this Javascript on your web page is granted
// provided that all of the code (including this copyright notice) is
// used exactly as shown
// [usare roman(numero, 0) per la notazione antica,
//  roman(numero, 1) per la notazione classica]
function roman(n,s) {
  var r = '';
  var d;
  var rn = new Array('IIII','V','XXXX','L','CCCC','D','MMMM');
  for (var i=0; i< rn.length; i++) {
    var x = rn[i].length+1;
    var d = n%x;
    r= rn[i].substr(0,d)+r;
    n = (n-d)/x;
    }
  if (s) {
    r=r.replace(/DCCCC/g,'CM');
    r=r.replace(/CCCC/g,'CD');
    r=r.replace(/LXXXX/g,'XC');
    r=r.replace(/XXXX/g,'XL');
    r=r.replace(/VIIII/g,'IX');
    r=r.replace(/IIII/g,'IV');
    }
  return r;
}
                  
function converti_array(stringa, int) {
var array = stringa.split(/[\s,]+/);
var new_array = new Array();
for (i=0; i<array.length; i++) {
  new_array.push("Q."+roman(array[i], int));
}
return new_array.join(", ");
}

function fill_UT(f) {
    f.ut.value = 'Rastello';
    }

// pendenza -- nota: siccome un quadrato è lungo 10m, moltiplico ancora per 10 per avere la percentuale. 
function fill_pendenza(f) {
  var primoMax = Math.max(Number(f.quota1.value), Number(f.quota2.value));
  var secondoMax = Math.max(Number(f.quota3.value), Number(f.quota4.value));
  var p =  ( Math.abs(primoMax - secondoMax)/10) * 100;
  f.pendenza.value = p.toPrecision(5);
  }

function fill_totaleReperti(f) {
  f.totale_totali_altri_reperti.value = Number(f.Monete_tot.value) +
			   Number(f.ElLiticiStrutturali_tot.value) +
			   Number(f.Metalli_tot.value) +
			   Number(f.Ossi_tot.value) +
			   Number(f.Vetro_tot.value)+
                           Number(f.ScartiDiFornace_tot.value)+
                           Number(f.Laterizi_tot.value)+
                           Number(f.Malta_tot.value)+
                           Number(f.NonID_tot.value);
                           

}
//
//
//function fill_totaleRepertiStrutturali(f) {
//  f.totale_reperti_strutturali.value = Number(f.Malta_tot.value) +
//				       Number(f.Coppi/Tegole_tot.value) +
//				       Number(f.ElementiLiticiStrutturali_tot.value) +
//				       Number(f.Laterizi_tot.value);
//}
//
//function fill_totaleCeramica(f) {
//  f.totale_totali_ceramiche.value = Number(f.Orli_tot.value) +
//			    Number(f.Fondi_tot.value) +
//			    Number(f.Pareti_tot.value) +
//			    Number(f.Becchi_tot.value) +
//			    Number(f.Anse_tot.value) +
//			    Number(f.Altro_tot.value);
//}

function fill_Piante(f) {
  
  f.piante_valore.value = 'P'+f.numero_quadrato.value.substring(1);
  
}

function somma_campi(a, b) {
return Number(a) + Number(b);
}

function resize(area) {
  var height = area.scrollHeight;
  var numberOfLines = Math.floor(height/20);
  area.rows = numberOfLines;
}
