package practicum.getfitla_v3;

import org.json.JSONObject;

/**
 * Created by dhuth on 1/14/2015.
 *
 * Interface for the callback function of an asynchronous task.
 * This allows different post-processing tasks to be determined on a per-instance basis.
 */
interface AsyncCallback {
    void onResult(JSONObject object);
}