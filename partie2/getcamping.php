<?php
//connexion 
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$con = mysqli_connect($host,$user_name,$password,$db_name);
mysqli_set_charset($con,'utf8');
$sql = "select * from camping;";
$result = mysqli_query($con,$sql);
$response = array();
while ($row = mysqli_fetch_array($result))
{
array_push($response,array("id_c"=>$row["id_c"],"destination_c"=>$row["destination_c"],"date_c"=>$row["date_c"],"prix_c"=>$row["prix_c"],"description_c"=>$row["description_c"],"image"=>$row["image"]) );	
}
echo json_encode($response);


?>