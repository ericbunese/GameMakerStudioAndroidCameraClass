/// @description Request camera
var request = androidCamera_open_camera("filename.jpg");
show_debug_message("CAMERA CALL STATUS: "+string(request))