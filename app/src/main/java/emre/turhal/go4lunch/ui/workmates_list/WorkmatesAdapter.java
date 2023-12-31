package emre.turhal.go4lunch.ui.workmates_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import emre.turhal.go4lunch.databinding.FragmentWorkmatesItemBinding;
import emre.turhal.go4lunch.models.Workmate;

public class WorkmatesAdapter extends RecyclerView.Adapter<WorkmatesViewHolder> {


    private List<Workmate> mWorkmatesList;

    public WorkmatesAdapter(List<Workmate> items) {
        mWorkmatesList = items;
    }

    @NonNull
    @Override
    public WorkmatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentWorkmatesItemBinding view = FragmentWorkmatesItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new WorkmatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkmatesViewHolder holder, int position) {
        holder.updateWithWorkmate(this.mWorkmatesList.get(position));


    }

    @Override
    public int getItemCount() {
        return mWorkmatesList.size();
    }

    public Workmate getWorkmates(int position) {
        return this.mWorkmatesList.get(position);
    }


}
