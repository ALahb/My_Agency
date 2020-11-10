<?php
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$con = mysqli_connect($host,$user_name,$password,$db_name);

$dd=$_POST["dd"];
$df=$_POST["df"];
$id_user = $_POST["id_user"];
$id_h = $_POST["id_h"];

 

$sql = "insert into reservation values ('','".$id_user."','".$id_h."','".$dd."','".$df."');";
$result = mysqli_query($con,$sql);
$response = array();
    $code = "reg_success";
	$message = "Reservation valide";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);


mysqli_close($con);	
?>