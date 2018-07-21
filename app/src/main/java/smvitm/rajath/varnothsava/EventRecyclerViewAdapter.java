package smvitm.rajath.varnothsava;

/*
Created by Rajath
For more details contact me at
Name : Rajath
Email : ykrajath4@gmail.com
WhatsApp : +91 9591708470
Phone : +91 9591708470
*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {
    private static final String LOG_TAG = EventRecyclerViewAdapter.class.getSimpleName();
    public static int position;
    ItemListener mListener;
    Context context;
    private ArrayList<RecyclerViewModel> mItems;

    public EventRecyclerViewAdapter(Activity context, ArrayList<RecyclerViewModel> program, ItemListener listener) {

        this.context = context;
        mItems = program;
        mListener = listener;
    }

    public void setOnItemClickListener(ItemListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.event_card_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }

    public interface ItemListener {
        void onItemClick(RecyclerViewModel pName, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public RecyclerViewModel pName;
        TextView name, detail;
        View textContainer;
        ImageView eventicon;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            cardView = itemView.findViewById(R.id.cvItem);
            name = itemView.findViewById(R.id.eventname);
            detail = itemView.findViewById(R.id.eventshortdetail);
            eventicon = itemView.findViewById(R.id.eventicon);
            textContainer = itemView.findViewById(R.id.text_container);
        }

        public void setData(RecyclerViewModel pName) {
            this.pName = pName;
            name.setText(pName.getEventName());
            detail.setText(pName.getEventShortDetail());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(pName, getAdapterPosition());
            }
            Intent gotoNext = new Intent(context, EventInfoInBriefActivity.class);
            gotoNext.putExtra("id", pName.getId());
            gotoNext.putExtra("EventName", pName.getEventName());
            gotoNext.putExtra("Rules", pName.getRules());
            gotoNext.putExtra("StudentCoordinator", pName.getStudentCoordinator());
            gotoNext.putExtra("FacultyCoordinator", pName.getFacultyCoordinator());
            gotoNext.putExtra("ContactDetailsOfStudentCoordinator", pName.getContactDetailsOfStudentCoordinator());
            gotoNext.putExtra("ContactDeatailOfFacCoordi", pName.getContactDeatailOfFacCoordi());
            gotoNext.putExtra("Venue", pName.getVenue());
            gotoNext.putExtra("EventShortDetail", pName.getEventShortDetail());
            gotoNext.putExtra("StudentCordinatorDetail", pName.getStudentCordinatorDetail());
            gotoNext.putExtra("FacCoordinatorDetail", pName.getFacCoordinatorDetail());
            gotoNext.putExtra("EventTime", pName.getEventTime());
            gotoNext.putExtra("EventDay", pName.getEventDay());
            gotoNext.putExtra("OtherRules", pName.getOtherRules());
            context.startActivity(gotoNext);
        }

    }
}