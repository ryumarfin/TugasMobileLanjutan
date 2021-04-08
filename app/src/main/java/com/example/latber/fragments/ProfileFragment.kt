package com.example.latber.fragments


import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import com.example.latber.QUOTES_DATA
import com.example.latber.QUOTES_SIGNAL
import com.example.latber.R
import com.example.latber.data.Quotes
import com.example.latber.jobScheduler.QuotesScheduler
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    //definisi JobID
    val JobId = 18000
    //membuat recevier untuk menangkap data yang dibroadcast/dikirim dari job scheduler
    private val QuotesReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //tangkap data yg dikirimkan
            val data = intent?.getParcelableExtra<Quotes>(QUOTES_DATA)
            Log.i("RESULTs",data?.text.toString())
            //tampilkan data yang ditangkap tadi ke dalam view/xml
            requireView().quotes.text = data?.text + "\n-" +  data?.author
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var objView = inflater.inflate(R.layout.fragment_profile, container, false)
        var btn = objView.findViewById<Button>(R.id.btn_logout)
        btn.setOnClickListener {
            getActivity()?.finish()
//            activity?.onBackPressed()
        }

        val intentFilter = IntentFilter(QUOTES_SIGNAL)
        //mendaftarkan receiver agar ProfileFragment dapat menangkap broadcast
        requireActivity().registerReceiver(QuotesReceiver,intentFilter)
        //panggil fungsi startMyJob untuk menjalankan job
        startMyJob()
        return objView
    }

    override fun onDestroy() {
        super.onDestroy()
        //menghentikan job
        cancelMyJob()
    }


    private fun startMyJob() {
        //definisikan service untuk menjalankan QuoteScheduler pada fragment ini
        var serviceComponent = ComponentName(requireContext(), QuotesScheduler::class.java)
        //buat JobInfo dan berikan JobID
        //berikan informasi tambahan yaitu dengan Network job dapat dijalankan
        val JobInfo = JobInfo.Builder(JobId, serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

        //definisikan job scheduler
        var JobQoutes = activity?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        //jalankan job schedulernya
        JobQoutes.schedule(JobInfo.build())
    }

    private fun cancelMyJob() {
        var JobQoutes = activity?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        //menghentikan job schedulernya dengan ID JobId
        JobQoutes.cancel(JobId)
    }

}