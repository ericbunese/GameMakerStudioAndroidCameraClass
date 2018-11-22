package ${YYAndroidPackageName};
import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import android.app.Activity;
import android.database.Cursor;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.lang.SecurityException;

import android.util.Log;

public class AndroidCameraClass extends RunnerSocial
{
    public static String path;
    private Uri pictureFileUri;
    private boolean canTakePicture = false;
    private static final int TAKE_PICTURE = 1; 
    private static final int EVENT_OTHER_SOCIAL = 70;
    private static final int PERMISSION_REQUEST_CODE = 100;
    
    public double androidCamera_init(String path)
    {
        AndroidCameraClass.path = String.valueOf(Environment.getExternalStorageDirectory())+path;

        File folder = new File(AndroidCameraClass.path);
        boolean created = false;
        if (!folder.exists())
        {
			created = folder.mkdir();
        }
        else created = true;

        if (created)
        {
            Log.i("yoyo","AndroidCameraClass Initialized successfully on "+AndroidCameraClass.path);
            canTakePicture = true;
            return 1.0;
        }
        else
        {
            Log.i("yoyo","AndroidCameraClass Failed to initialize on "+AndroidCameraClass.path);
            return -1.0;
        }
    }

    public double androidCamera_open_camera(String filename)
    {
        try
		{
            if (canTakePicture)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File picture = new File(AndroidCameraClass.path, filename);
                pictureFileUri = Uri.fromFile(picture);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picture));
                RunnerActivity.CurrentActivity.startActivityForResult(intent, TAKE_PICTURE);
                canTakePicture = false;
                return 1.0;
            }
            else 
            {
                Log.i("yoyo","AndroidCameraClass Cannot take more than one picture. Please wait for the result of the previous call or check for initialization failure.");
                return -2.0;
            }
		}
		catch (SecurityException e)
		{
            Log.i("yoyo","AndroidCameraClass Caught a SecurityException on camera open. Please check for user permission.");
			return -1.0;
		}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
        RunnerJNILib.DsMapAddString(dsMapIndex, "type", "androidCamera_picture");
        switch (requestCode)
        {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK)
                {
                    RunnerJNILib.DsMapAddString(dsMapIndex, "success", "true");
                    RunnerJNILib.DsMapAddString(dsMapIndex, "filename", pictureFileUri.toString());
                    canTakePicture = true;
                    RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);   
                }
                else
                {
                    RunnerJNILib.DsMapAddString(dsMapIndex, "success", "false");
                    RunnerJNILib.DsMapAddString(dsMapIndex, "filename", "");
                    RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL); 
                }
                break;
        }
    }

    public double androidCamera_request_permissions()
    {
        if ((ContextCompat.checkSelfPermission(RunnerActivity.CurrentActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) || 
            (ContextCompat.checkSelfPermission(RunnerActivity.CurrentActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) ||
            (ContextCompat.checkSelfPermission(RunnerActivity.CurrentActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED))
        {
            Log.i("yoyo","AndroidCameraClass Requesting user permissions...");
            ActivityCompat.requestPermissions(RunnerActivity.CurrentActivity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            return 1.0;
        }
        else
        {
            Log.i("yoyo","AndroidCameraClass User already granted permission for required access.");
            return -1.0;
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE)
        {
            int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
            RunnerJNILib.DsMapAddString(dsMapIndex, "type", "androidCamera_permissions");
            int totalGrantedPermissions = 0;
            for (int i=0;i<grantResults.length;++i)
            {
                totalGrantedPermissions += (grantResults[i] == PackageManager.PERMISSION_GRANTED);
            }
            if (totalGrantedPermissions == grantResults.length)
            {
                RunnerJNILib.DsMapAddString(dsMapIndex, "success", "true");
                Log.i("yoyo","AndroidCameraClass User granted all permissions to AndroidCameraClass.");
            }
            else
            {
                RunnerJNILib.DsMapAddString(dsMapIndex, "success", "false");
                Log.i("yoyo","AndroidCameraClass User did not grant all required permissions.");
            }
        }
    }*/
}