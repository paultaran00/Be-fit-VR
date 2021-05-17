package com.unity3d.player;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class UnityPermissions {
    private static final String SKIP_DIALOG_METADATA_NAME = "unityplayer.SkipPermissionsDialog";

    private static boolean checkInfoForMetadata(PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean(SKIP_DIALOG_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasUserAuthorizedPermission(Activity activity, String str) {
        return activity.checkCallingOrSelfPermission(str) == 0;
    }

    public static void requestUserPermissions(Activity activity, String[] strArr, IPermissionRequestCallbacks iPermissionRequestCallbacks) {
        if (PlatformSupport.MARSHMALLOW_SUPPORT && activity != null && strArr != null) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.findFragmentByTag("96489") == null) {
                C0059e eVar = new C0059e(activity, iPermissionRequestCallbacks);
                Bundle bundle = new Bundle();
                bundle.putStringArray("PermissionNames", strArr);
                eVar.setArguments(bundle);
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(0, eVar, "96489");
                beginTransaction.commit();
            }
        }
    }

    public static boolean skipPermissionsDialog(Activity activity) {
        if (!PlatformSupport.MARSHMALLOW_SUPPORT) {
            return false;
        }
        try {
            PackageManager packageManager = activity.getPackageManager();
            return checkInfoForMetadata(packageManager.getActivityInfo(activity.getComponentName(), 128)) || checkInfoForMetadata(packageManager.getApplicationInfo(activity.getPackageName(), 128));
        } catch (Exception unused) {
        }
    }
}
