// Banknotes vars
var billet5, billet10, billet20, billet50, billet100, billet200, billet500;

// Total price var
var totalPrice;

// Arrays for the plates and for price per plate
var menu = new Array();
var prices= new Array();

// Ask for the five plates and prices
for (var i=0;i<5;i++) {
  menu[i]=prompt("Introdueix el nom del plat " + (i+1)).toLowerCase();
  prices[i]=prompt("Introdueix el seu preu");
}

// List to fill in the order
var comanda = new Array();

// Int to stop ordering
var more = 1;

while (more==1){
  comanda.push(prompt("Aquest és el nostre menú d'avui:\n\n" + menu[0] + " - " + prices[0] + " €\n" + menu[1] + " - " + prices[1] + " €\n" + menu[2] + " - " + prices[2] + " €\n" + menu[3] + " - " + prices[3] + " €\n" + menu[4] + " - " + prices[4] + " €\n\nEscull un plat del menú.").toLowerCase());

  more=-1;
  while (more!=1 && more!=0) {
    more=prompt("Ha escollit vosté: " + comanda[comanda.length-1] + ".\n\nVol afegir algún plat més a la comanda? (1: Sí / 0: No)");
  }  
}

// We'll have in nonAvailable var the list of ordered plates not available
// At the end of the function 'comanda' will contain the number of each plate ordered,
// in the same order as they are in 'menu'. 
//That is, [2,0,0,1,0] if the order includes two plates of menu[0] and one of menu[3]
var nonAvailable=checkOrder();

document.write("<h1>Menú del dia:</h1>");
for (var i=0;i<menu.length;i++) {
  document.write(menu[i]+" - "+prices[i]+" €<br>");
}

document.write("<h1>La seva comanda:</h1>");
document.write("Plats demanats no disponibles: "+nonAvailable);
document.write("<br>=======================");
document.write("<br>Plats demanats disponibles:<br>");
var totalPrice=0;
for (var i=0;i<menu.length;i++) {
  if (comanda[i]>0) {
    document.write(menu[i] + " (" + comanda[i] + ") - " + (prices[i]*comanda[i]) + "€<br>");
    totalPrice+=prices[i]*comanda[i];
  }
}
document.write("=======================<br>");
document.write("Preu total: "+totalPrice+"€<br>");
document.write("<br>Pot pagar amb:<br>");

// Calculate the number of each banknote to pay
getBanknotes();

if (billet500 > 0) document.write(billet500 + " billet(s) de 500€<br>");
if (billet200 > 0) document.write(billet200 + " billet(s) de 200€<br>");
if (billet100 > 0) document.write(billet100 + " billet(s) de 100€<br>");
if (billet50 > 0) document.write(billet50 + " billet(s) de 50€<br>");
if (billet20 > 0) document.write(billet20 + " billet(s) de 20€<br>");
if (billet10 > 0) document.write(billet10 + " billet(s) de 10€<br>");
if (billet5 > 0) document.write(billet5 + " billet(s) de 5€<br>");

function checkOrder(){
  
  var nonAvailable=new Array();
  var ordered=[0,0,0,0,0];
  
  for (var i=0;i<comanda.length;i++){
    var found=false;
    for (var j=0;j<menu.length && !found;j++) {
      if (menu[j] == comanda[i]) {
        ordered[j]++;
        found=true;
      }
    }
    if (!found) nonAvailable.push(comanda[i]);
  }
  comanda=ordered;  
  return nonAvailable;
}
               
function getBanknotes() {      
  
  // Round price
  var roundPrice=Math.ceil(totalPrice);
  
  billet500=Math.floor(roundPrice/500);
  roundPrice%=500;
  if (roundPrice>485) {billet500++;return;}
  // 0 <= roundPrice < 485
  billet200=Math.floor(roundPrice/200);
  roundPrice%=200;
  // 0 <= roundPrice < 200, if billet200==2 --> roundPrice < 85
  if (roundPrice>185) {billet200++;return;}
  // 0 <= roundPrice <= 185, if roundPrice >= 85 --> billet200<2
  billet100=Math.floor(roundPrice/100);
  roundPrice%=100;
  // 0 <= roundPrice < 100
  if (roundPrice>85) {
      if (billet100 == 0) {
          billet100++;
          return;
      } else {
          billet100 = 0;
          billet200++;
          return;
      }
  }
  billet50=Math.floor(roundPrice/50);
  roundPrice%=50;
  // 0 <= roundPrice <= 49
  if (roundPrice>45) {billet50++; return;}
  billet20=Math.floor(roundPrice/20);
  roundPrice%=20;
  // 0 <= roundPrice <= 15
  if (roundPrice>15) {billet20++;return;}
  billet10=Math.floor(roundPrice/10);
  roundPrice%=10;
  if (roundPrice>5) {billet10++;return;}
  // 0 <= roundPrice <= 5
  billet5=Math.floor(roundPrice/5);
  if (roundPrice%5 > 0) billet5++;
}