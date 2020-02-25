// Banknotes vars
var billet5, billet10, billet20, billet50, billet100, billet200, billet500;

// Total price var
var totalPrice;

// Arrays for the plates and for price per plate
var menu = new Array();
var prices= new Array();

// Ask for the five plates and prices
for (var i=0;i<5;i++) {
  menu[i]=prompt("Introdueix el nom del plat " + (i+1));
  prices[i]=prompt("Introdueix el seu preu");
}

// List to fill in the order
var comanda = new Array();

// Int to stop ordering
var more = 1;

while (more==1){
  comanda.push(prompt("Aquest és el nostre menú d'avui:\n\n" + menu[0] + " - " + prices[0] + " €\n" + menu[1] + " - " + prices[1] + " €\n" + menu[2] + " - " + prices[2] + " €\n" + menu[3] + " - " + prices[3] + " €\n" + menu[4] + " - " + prices[4] + " €\n\nEscull un plat del menú."));

  more=-1;
  while (more!=1 && more!=0) {
    more=prompt("Ha escollit vosté: " + comanda[comanda.length-1] + ".\n\nVol afegir algún plat més a la comanda? (1: Sí / 0: No)");
  }  
}

// Testing
document.write("<h1>Comanda escollida:</h1>");
document.write("<h2>"+comanda+"</h2>");