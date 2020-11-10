<?php
//connexion 
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$con = mysqli_connect($host,$user_name,$password,$db_name);
mysqli_set_charset($con,'utf8');
$sql = "select * from dhote;";
$result = mysqli_query($con,$sql);
$response = array();
while ($row = mysqli_fetch_array($result))
{
array_push($response,array("id_h"=>$row["id_h"],"empmacement_h"=>$row["empmacement_h"],"lati"=>$row["lati"],"longi"=>$row["longi"],"prix_h"=>$row["prix_h"],"image"=>$row["image"],"nom_h"=>$row["nom_h"]) );	
}
echo json_encode($response);


?>