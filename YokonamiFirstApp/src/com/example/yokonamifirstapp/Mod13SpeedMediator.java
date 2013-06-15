package com.example.yokonamifirstapp;
import java.util.*;
import android.widget.*;
import android.content.ClipData;
import android.content.ClipDescription;
import android.view.*;
import android.content.Context;
import android.app.Activity;

public class Mod13SpeedMediator extends View implements Mediator{
	private ArrayList<SeatColleague> colleagueList;
	private ArrayList<SeatColleague> talonList;
	private ArrayList<Tramp> deck;
	private HashMap<Integer, Integer> inverseList;
	private TextView textRestOpponent;
	private TextView textRestMe;
	private int restOpponent;
	private int restMe;
	public static final int N_TALON = 2;
	public static final int N_HAND = 4;
	private Context context;
	private Toast toast;
	private boolean isExistToast;

	public Mod13SpeedMediator(Context context){
		super(context);
		this.context = context;
		colleagueList = new ArrayList<SeatColleague>();
		talonList = new ArrayList<SeatColleague>();
		restOpponent = 0;
		restMe = 0;
		isExistToast = false;
		inverseList = new HashMap<Integer,Integer>();
		inverseList.put(1, 1);
		inverseList.put(2, 7);
		inverseList.put(3, 9);
		inverseList.put(4, 10);
		inverseList.put(5, 8);
		inverseList.put(6, 11);
		inverseList.put(7, 2);
		inverseList.put(8, 5);
		inverseList.put(9, 3);
		inverseList.put(10, 4);
		inverseList.put(11, 6);
		inverseList.put(12, 12);
		inverseList.put(13, -1);
		
	}
	public boolean canPut(int strategy, Tramp willPut){
		boolean returnValue = false;
		ArrayList<Integer> availables = new ArrayList<Integer>(); 
		if(strategy == 1){//mod13
			int one = talonList.get(0).getTopTramp().getNumber();
			int another = talonList.get(1).getTopTramp().getNumber();
			int one_inverse = inverseList.get(one);
			int another_inverse = inverseList.get(another);
			int hand = willPut.getNumber();
			if(hand == 13){
				hand = 0;
			}
			int offset = 13;
			int mod = 13;
			// add
			availables.add((one + another)%mod);
			// subtract
			availables.add((one - another + offset)%mod);
			availables.add((another-one + offset)%mod);
			// multiply
			availables.add((one * another)%mod);
			// division
			availables.add((one * another_inverse)%mod);
			availables.add((one_inverse * another)%mod);
			returnValue = availables.contains(hand);
		}
		return returnValue;
	}
	public void updateRest(){
		textRestMe.setText(String.valueOf(restMe));
		textRestOpponent.setText(String.valueOf(restOpponent));
	}
	public void refleshTalon(){
		for(SeatColleague p : talonList){
			int size = deck.size();
			if(size>0){
				p.setTopTramp(deck.remove(size-1));
			}else{
				showToast("game end");
			}
		}
	}
	public boolean offerServe(SeatColleague seat){
		int size = this.deck.size();
		if(size > 0){
			seat.setTopTramp(this.deck.remove(size-1));
			if(((DraggableSeat)seat).getOwner().equals("me")){
				restMe += 1;
			}else{
				restOpponent += 1;
			}
			this.updateRest();
			return true;
		}else{
			showToast("game end");
			return false;
		}
	}
	@Override
	public void createColleagues(Context context) {
		//ViewGroup layout = context.findViewById(R.id.layout_activity_drag_drop);
		LinearLayout layout_opponent_hand = (LinearLayout)((Activity)context).findViewById(R.id.layout_activity_drag_drop_opponent_hand);
		LinearLayout layout_talon = (LinearLayout)((Activity)context).findViewById(R.id.layout_activity_drag_drop_talon);
		LinearLayout layout_my_hand = (LinearLayout)((Activity)context).findViewById(R.id.layout_activity_drag_drop_my_hand);
		// TODO Auto-generated method stub
		
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(
				  LinearLayout.LayoutParams.WRAP_CONTENT,
				  LinearLayout.LayoutParams.WRAP_CONTENT));
		param.weight = 1.0f;
		param.gravity = Gravity.CENTER_VERTICAL;
		//textRestMe = new TextView(context);
		//textRestOpponent = new TextView(context);
		textRestMe = new Button(context);
		textRestOpponent = new Button(context);
		textRestMe.setText(String.valueOf(restMe));
		textRestOpponent.setRotation(180);
		//textRestOpponent.setTextSize(15);
		//textRestMe.setTextSize(15);
		textRestOpponent.setText(String.valueOf(restOpponent));



		for(int i=0; i<N_TALON; i++){
			PuttableSeat pSeat = new PuttableSeat(context);
			
			pSeat.setMediator(this);
			pSeat.setOnDragListener(new myDragEventListener());
			pSeat.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					showToast(inverseList.toString(),Toast.LENGTH_LONG);
					return true;
				}
			});
			param = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(
					  LinearLayout.LayoutParams.WRAP_CONTENT,
					  LinearLayout.LayoutParams.WRAP_CONTENT));
			param.weight = 1.0f;
			
			layout_talon.addView(pSeat, param);
			this.colleagueList.add(pSeat);
			this.talonList.add(pSeat);
			//reflesh button
			if(i==0){
				LinearLayout layout_information = new LinearLayout(context);
				layout_information.setOrientation(1);
				param.gravity = Gravity.CENTER_VERTICAL;
				layout_talon.addView(layout_information, param);
				Button refleshButton = new Button(context);
				refleshButton.setText("ü");
				refleshButton.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						refleshTalon();
					}
				});
				param = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(
						  LinearLayout.LayoutParams.WRAP_CONTENT,
						  LinearLayout.LayoutParams.WRAP_CONTENT));
				param.weight = 1.0f;
				param.gravity = Gravity.CENTER_VERTICAL;
				layout_information.addView(textRestOpponent, param);
				layout_information.addView(refleshButton, param);
				layout_information.addView(textRestMe, param);
			}
		}

		for(int i=0; i<N_HAND; i++){
			for(int j=0; j<2; j++){
				DraggableSeat dSeat = null;
				if(j==0){
					dSeat = new DraggableSeat(context, "opponent");
				}else{
					dSeat = new DraggableSeat(context, "me");		
				}

				dSeat.setMediator(this);
				dSeat.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						ClipData.Item item = new ClipData.Item(String.valueOf(v.getTag()));
						ClipData dragData = new ClipData(String.valueOf(v.getTag()), new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},item);
						dragData.addItem(item);
						View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
						v.startDrag(dragData,  // the data to be dragged
		                        	myShadow,  // the drag shadow builder
		                        	(Object)v,      // itself
		                        	0          // flags (not currently used, set to 0)
								);
						System.out.println("onTouch");
						return true;
					}
				});
			this.colleagueList.add(dSeat);
			param = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(
					  LinearLayout.LayoutParams.WRAP_CONTENT,
					  LinearLayout.LayoutParams.WRAP_CONTENT));
			param.weight = 1.0f;
			
			if(j==0){
				layout_opponent_hand.addView(dSeat, param);
			}else{
				layout_my_hand.addView(dSeat, param);				
			}
			}
		}
	}
	public void startGame(){
		deck = new ArrayList<Tramp>();
		for(int i=0; i<Tramp.N_SUIT*Tramp.N_NUMBER; i++){
			int serialNumber = i;
			int suit = serialNumber%4;
			int number = serialNumber%13 + 1;
			String resName = "";
			resName = resName + Tramp.suitExpression[suit].substring(0,1);
			if(number < 10){
				resName = resName + "0";
			}
			resName = resName + number;
			System.out.println(resName);
			int resID = getResources().getIdentifier(resName, "drawable", "com.example.yokonamifirstapp");
			Tramp tramp = new Tramp(i, resID);
			deck.add(tramp);
		}
		Collections.shuffle(deck);
		for(SeatColleague s : colleagueList){
			int size = deck.size();
			if(size>0){
				s.setTopTramp(deck.remove(size-1));
			}else{
				System.out.println("USER_ERROR: trampList's size = 0");
			}
		}
		showToast("game start");
	}
	public void showToast(String s){
		if(isExistToast){
			toast.cancel();
		}
		toast = Toast.makeText(context, s,Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		isExistToast = true;
		
	}
	public void showToast(String s, int length){
		if(isExistToast){
			toast.cancel();
		}
		toast = Toast.makeText(context, s,length);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		isExistToast = true;
	}
	
}
