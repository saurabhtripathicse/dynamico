package org.ecloga.dynamico.style;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

final class ParamsFactory {

    private View view;
    private JSONObject attributes;

    ParamsFactory(View view, JSONObject attributes) {
        this.view = view;
        this.attributes = attributes;
    }

    ViewGroup.MarginLayoutParams getParams() throws Exception {
        if(attributes.get("parent_class").equals(RelativeLayout.class)) {
            return getRelative();
        }

        return getLinear();
    }

    private ViewGroup.MarginLayoutParams getLinear() throws Exception {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();

        if(params == null) {
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if(attributes.has("layout_weight")) {
            params.weight = (float) attributes.getDouble("layout_weight");
        }

        if(attributes.has("layout_gravity")) {
            String gravity = attributes.getString("layout_gravity");

            if(gravity.equalsIgnoreCase("start")) {
                params.gravity = Gravity.START;
            }else if(gravity.equalsIgnoreCase("top")) {
                params.gravity = Gravity.TOP;
            }else if(gravity.equalsIgnoreCase("end")) {
                params.gravity = Gravity.END;
            }else if(gravity.equalsIgnoreCase("bottom")) {
                params.gravity = Gravity.BOTTOM;
            }else if(gravity.equalsIgnoreCase("center")) {
                params.gravity = Gravity.CENTER;
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                params.gravity = Gravity.CENTER_HORIZONTAL;
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                params.gravity = Gravity.CENTER_VERTICAL;
            }
        }

        return params;
    }

    private ViewGroup.MarginLayoutParams getRelative() throws Exception {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();

        if(params == null) {
            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if(attributes.has("center_horizontal") && attributes.getBoolean("center_horizontal")) {
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }

        if(attributes.has("center_vertical") && attributes.getBoolean("center_vertical")) {
            params.addRule(RelativeLayout.CENTER_VERTICAL);
        }

        if(attributes.has("center_in_parent") && attributes.getBoolean("center_in_parent")) {
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
        }

        if(attributes.has("align_parent_start") && attributes.getBoolean("align_parent_start")) {
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
        }

        if(attributes.has("align_parent_top") && attributes.getBoolean("align_parent_top")) {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }

        if(attributes.has("align_parent_end") && attributes.getBoolean("align_parent_end")) {
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
        }

        if(attributes.has("align_parent_bottom") && attributes.getBoolean("align_parent_bottom")) {
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }

        return params;
    }
}