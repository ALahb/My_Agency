<?php
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$user_id=$_POST["user_id"];
$con = mysqli_connect($host,$user_name,$password,$db_name);
mysqli_set_charset($con,'utf8');
$products = "select * from reservation where id_user = '".$user_id."';";
if ($result1 = mysqli_query($con, $products)) {     
    while ($obj = mysqli_fetch_object($result1)) {        
		$id_h = $obj->id_h;		
		$sql = "select * from dhote where id_h = $id_h;";
		$result = mysqli_query($con,$sql);
		$response = array();
	while ($row = mysqli_fetch_array($result))
{
array_push($response,array("date_debut"=>$obj->date_debut,"date_fin"=>$obj->date_fin,"nom_h"=>$row["nom_h"]));	
}
echo json_encode($response);
    }

    
    mysqli_free_result($result1);
}

?>