/// @description Async callbacks.
var type = async_load[?"type"];

// Print out the given event.
show_debug_message("ASYNC EVENT RECEIVED: "+json_encode(async_load))

// Test if it came from this extension (always begins with 'androidCamera')
if (string_pos("androidCamera", type)!=0)
{
	// If it's a picture
	if (string_pos("_picture", type)!=0)
	{
		// Was the picture taken?
		if (async_load[?"success"]=="true")
		{
			// Replace protocol and escaping characters from string.
			var filename = string_replace(string_replace_all(async_load[?"filename"], "\\", ""), "file://", "");
			show_debug_message(filename)
			// Delete previous sprites to prevent memory leaks and add new sprite.
			if (sprite_exists(sprite))
			{
				sprite_delete(sprite)
			}
			var spr = sprite_add(filename, 0, 0, 0, 0, 0);
			sprite = spr
			sprite_index = sprite
		}
		else
		{
		 //Failed to capture image - attempt to request permissions
			show_debug_message("CAMERA PERMISSION STATUS: "+string(androidCamera_request_permissions()))
		}
	}
	/*else if (string_pos("_permissions", type)!=0) // I commented this out because the callback for this is disabled on java file. Read the readme for more information.
	{
		if (async_load[?"success"]=="false")
		{
			//gibe permission please
		}
	}*/
}