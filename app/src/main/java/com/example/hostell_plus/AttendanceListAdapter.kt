package com.example.hostell_plus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttendanceListAdapter(val attendanceList: List<AttendanceData>) :
    RecyclerView.Adapter<AttendanceListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentNameTextView: TextView = itemView.findViewById(R.id.studentNameTextView)
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_attendance_list_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAttendance = attendanceList[position]
        holder.studentNameTextView.text = currentAttendance.studentName
        holder.timestampTextView.text = currentAttendance.timestamp.toString()
    }

    override fun getItemCount(): Int {
        return attendanceList.size
    }
}
