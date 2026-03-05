<?php
    define("DIMTAB", 10);
    //Fonction qui crèer un tableau de multiplication dimTab x dimTab
    function tableDeMulti(int $dimTab=DIMTAB){
        $buffer="<table>\n\t<caption>Table de multiplication ".$dimTab."x".$dimTab."</caption>\n\t\t<thead>";
        for($i=0; $i<=$dimTab; $i++){
            $buffer=$buffer."\n\t\t<tr>\n\t\t"; 
            for($j=0; $j<=$dimTab; $j++){
                if($i==0){
                    if($j==0){
                        $buffer=$buffer."<th>X</th>"; 
                    }else{
                    $buffer=$buffer."<th>$j</th>";
                    }
                }else if($j==0){
                    $buffer=$buffer."<th>$i</th>";
                }else{
                    $buffer=$buffer."<td>".$i*$j."</td>";
                }
            }
            //fermeture de la ligne
            if($i==0){
                        $buffer.="\n\t\t</tr>\n\t\t</thead>";
                    }
            else{
                $buffer.="\n\t\t</tr>";
                }
            }
        $buffer=$buffer."</table>\n"; 
        return $buffer;
    }

//fonction qui prend en parametre les 
function rgbToHex($r, $g, $b){
	$buffer = "#";
	$buffer = $buffer . sprintf("%02X", $r);
	$buffer = $buffer . sprintf("%02X", $g);
	$buffer = $buffer . sprintf("%02X", $b);
	return $buffer;
}

function hexToRgb($hex, &$r, &$g, &$b){
	if($hex[0] == "#"){
		$hex = substr($hex, 1);
	}
	if(strlen($hex) == 3){
		$r = hexdec($hex[0] . $hex[0]);
		$g = hexdec($hex[1] . $hex[1]);
		$b = hexdec($hex[2] . $hex[2]);
		return true;
	}
	if(strlen($hex) == 6){
		$r = hexdec(substr($hex,0,2));
		$g = hexdec(substr($hex,2,2));
		$b = hexdec(substr($hex,4,2));
		return true;
	}
	return false;
}

//convertion numero romains en decimale
    function rom(String $nbRom){
        $buffer = 0 ;
        $i = strlen($nbRom) - 1;
        while($i >= 0) {

            // on verifie la valeur du chiffre romain
            switch($nbRom[$i]){
                case "M" :
                    $ajout = 1000 ;
                    break ;
                case "D" :
                    $ajout = 500 ;
                    break ;
                case "C" :
                    $ajout = 100 ;
                    break ;
                case "L" :
                    $ajout = 50 ;
                    break ;
                case "X" :
                    $ajout = 10 ;
                    break ;
                case "V" :
                    $ajout = 5 ;
                    break ;
                case "I" :
                    $ajout = 1 ;
                    break ;
            }

            // tant qu'il n'est pas vide on verifie si le numero de gauche est plus petit afin de le soustraire
            if($i != 0){
                $prec = 0 ;

                switch($nbRom[$i - 1]){
                    case "M" :
                        $prec = 1000 ;
                        break ;
                    case "D" :
                        $prec = 500 ;
                        break ;
                    case "C" :
                        $prec = 100 ;
                        break ;
                    case "L" :
                        $prec = 50 ;
                        break ;
                    case "X" :
                        $prec = 10 ;
                        break ;
                    case "V" :
                        $prec = 5 ;
                        break ;
                    case "I" :
                        $prec = 1 ;
                        break ;
                }
                if ($prec < $ajout){
                    $ajout = $ajout - $prec ;
                    $i--;
                }
            }

            // on ajoute enfin chaque valeur au total 
            $buffer = $buffer + $ajout ;
            $i--;
        }
        return $buffer ;
    }

    //fonction qui renvoie le tableau ASCII à partir du 32e élément
    function tab(){

        //partie qui permet de creer le tableau ainsi que la premiere ligne qui contient les nombres en hexadecimal
        $buffer = "<table style = 'border-style: dotted'>\n\t<caption>Tableau ASCII</caption>\n\t<thead>\n\t\t<tr><th></th>" ;
        for ($i = 0 ; $i <= 15 ; $i++) { 
            $buffer = $buffer."<th>".dechex($i)."</th>";
        }
        $buffer = $buffer."\n\t\t</tr>\n\t</thead>\n\t<tbody>" ;

        $i = 32 ;
        $k = 2 ;

        //partie qui permet de remplir ligne par ligne de 16 éléments ascii
        while ($i <= 127) { 
            $j = $i + 15 ;
            
            //numero de la ligne
            $buffer = $buffer."\n\t\t<tr>\n\t\t<td>".$k."</td>" ;

            
            for ($l = $i; $l <= $j ; $l++) { 

                //on verifie si les elements sont des chiffres ou des lettres (majuscules et minuscules) pour pouvoir leur donner une couleur
                if ($l> 47 && $l < 58)
                    $buffer = $buffer."<td class='lettresGris' style ='color : red'>" ;//les lettres et les chifres sont en gris
                else if ($l> 64 && $l < 91)
                    $buffer = $buffer."<td class='lettresGris' style ='color : green'>"  ;
                else if ($l> 96 && $l < 123)
                    $buffer = $buffer."<td class='lettresGris' style ='color : blue'>" ;
                else 
                    $buffer = $buffer."<td>" ;

                //on traite les cas spécial pour eviter des soucis avec html
                if (chr($l)=='>')
                    $buffer = $buffer."&gt;</td>" ;
                else if (chr($l)=='<')
                    $buffer = $buffer."&lt;</td>" ;
                else if (chr($l)=='&')
                    $buffer = $buffer."&amp;</td>" ;
                else if ($l==127)
                    $buffer = $buffer."&#x00A0;</td>" ;
                else 
                    $buffer = $buffer.chr($l)."</td>" ;
                    
            }

            $buffer = $buffer."\n\t\t</tr>" ;

            $k++ ;
            $i = $i + 16 ;
        }

        $buffer = $buffer."\n\t</tbody>\n</table>" ;
        return $buffer ;

    }    
?>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>TD6</title>		
		<meta charset="UTF-8">
		<meta name="description" content="Exercice du td6 sur les fonction php">
		<meta name="author" content="Chouaref, Jean and Loukili">
        <link rel="stylesheet" href="style2.css">
        <style>
            table{
                text-align: center;
            }
            tr,td,th{
                border: 2px solid;
                padding: 10px;
            }
            .lettresGris{
                background-color: lightgrey;
            }
            
        </style>
    </head>
    <body>

    <header>
    <h1 style="text-align: center">TD6 : XHTML5 & PHP (tests, boucles et fonctions)</h1><!--style local-->
    </header>   

    <main>
    <aside>
        <h2>Sommaire</h2>
        <ul>
        <li><a href="#exercice1">Exercice 1</a></li>
        <li><a href="#exercice2">Exercice 2</a></li>
        <li><a href="#exercice3">Exercice 3</a></li>
        <li><a href="#exercice4">Exercice 4</a></li>
        </ul>
    </aside>

    <section>
        <h2>Résultats des exercices</h2>
    <article id="exercice1">
        <h3>Exercice 1</h3>
<?php
echo tableDeMulti(); 
echo tableDeMulti(5);
?>
    </article>

    <article id="exercice2">
    <h3>Exercice 2</h3>
    <ul>
        <?php
            echo "<li>(255,0,128) en hexadécimal donne " . rgbToHex(255,0,128). "</li>";

            $r = 0;
            $g = 0;
            $b = 0;

            if(hexToRgb("#FF0080", $r, $g, $b)){
                echo "<li>#FF0080 donne r=". $r. " g=". $g. " b=". $b. "</li>";
            }
            else{
                echo "<li>Erreur de conversion</li>";
            }
        ?>
    </ul>
    </article>

    <article id="exercice3">
        <h3>Exercice 3</h3>
        <p> 
        <?php
        echo "MCCIV devient : ".rom("MCCIV")."\n";
        ?>
        </p>
    </article>

    <article id="exercice4">
        <h3>Exercice 4</h3>
    <?php
    echo tab()."\n";
    ?>
    </article>

</section>

</main>

<footer>
    <p>04/03/2026 - TD6 DevWeb, réalisé par <em>Chouaref</em>, <em>Jean</em> et <em>Loukili</em></p>
</footer>

</body>
</html>