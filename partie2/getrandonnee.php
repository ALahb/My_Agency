<?php
//connexion 
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$con = mysqli_connect($host,$user_name,$password,$db_name);
mysqli_set_charset($con,'utf8');
$sql = "select * from randonnee;";
$result = mysqli_query($con,$sql);
$response = array();
while ($row = mysqli_fetch_array($result))
{
array_push($response,array("id_r"=>$row["id_r"],"destination_r"=>$row["destination_r"],"date_r"=>$row["date_r"],"prix_r"=>$row["prix_r"],"description_r"=>$row["description_r"],"image"=>$row["image"]) );	
}
echo json_encode($response);


?>