package com.example.yokonamifirstapp;

import android.view.DragEvent;
import android.graphics.PorterDuff;
import android.content.ClipDescription;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnDragListener;
import android.graphics.Color;

public class myDragEventListener implements OnDragListener {
	private DragEvent firstEvent;
	@Override
	public boolean onDrag(View view, DragEvent event) {
		// TODO Auto-generated method stub
		PuttableSeat v = (PuttableSeat)view;
		final int action = event.getAction();
		switch(action){
		case DragEvent.ACTION_DRAG_STARTED:
        	//only when started?
            if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                //v.setColorFilter(Color.BLUE,PorterDuff.Mode.DARKEN);
            	v.changeState(State.PUTTABLE);
                v.invalidate();
                this.firstEvent = event;
                return(true);
            }else{
                return(false);
            }	
   		case DragEvent.ACTION_DRAG_ENTERED:
   			v.changeState(State.UNDER_DRAGGABLE);
			//v.setColorFilter(Color.GREEN,PorterDuff.Mode.DARKEN);
			return(true);
   		case DragEvent.ACTION_DRAG_EXITED:
   			v.changeState(State.PUTTABLE);
   			return(true);
        case DragEvent.ACTION_DRAG_LOCATION:
            return(true);
        case DragEvent.ACTION_DRAG_ENDED:
        	v.informDropped((DraggableSeat)firstEvent.getLocalState());
        	v.changeState(State.NEUTRAL);
        	v.invalidate();
        	//v.informDropped((String)firstEvent.getClipDescription().getLabel());
        	//System.out.println("asdfasdf" + firstEvent + firstEvent.getClipDescription());
        	//System.out.println("asdfasdf" + firstEvent.getClipDescription().getLabel());
        	//v.informDropped((String)firstEvent.getClipData().getItemAt(0).getText());
            //v.clearColorFilter();
/*            if (event.getResult()) {
                Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG);
            };
*/
            return(true);
        default:
            //Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
        break;
		};
		return false;
	}

}
