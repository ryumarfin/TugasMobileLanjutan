package com.example.latber.fragments


import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.latber.QUOTES_DATA
import com.example.latber.QUOTES_SIGNAL
import com.example.latber.R
import com.example.latber.activities.done
import com.example.latber.data.Quotes
import com.example.latber.jobScheduler.QuotesScheduler
import com.example.latber.sharePreferences.DataSharedPref
import com.example.latber.sharePreferences.SharePrefHelper
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.et_email
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.io.File

//nama file shared preference
private val PrefFileName = "MYFILEPREFDATA"

class ProfileFragment : Fragment() {

    //inisialisasi var mRewardVid
    private var mRewardVid : RewardedAd?= null

    var coin :Int  = 0
    var member : Boolean = false


//    //definisi JobID
//    val JobId = 18000
//    //membuat recevier untuk menangkap data yang dibroadcast/dikirim dari job scheduler
//    private val QuotesReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            //tangkap data yg dikirimkan
//            val data = intent?.getParcelableExtra<Quotes>(QUOTES_DATA)
//            Log.i("RESULTs",data?.text.toString())
//            //tampilkan data yang ditangkap tadi ke dalam view/xml
//            requireView().quotes.text = data?.text + "\n-" +  data?.author
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fa = File("/data/data/com.example.latber/shared_prefs/"+ PrefFileName +".xml")
        //cek apakah file sharedpref exist
        if (fa.exists()) {
            Log.d("TAG_PROFILE", PrefFileName + " exist")

            //inisialisasi
            var mySharedPref = DataSharedPref(context!!, PrefFileName)
            //mengambil data dari file sharedpref
            coin = mySharedPref.Rewardcoin!!
            member = mySharedPref.VIP_member!!

        }
        //jika file tidak ditemukan
        else {
            Log.d("TAG_PROFILE", PrefFileName + " no exist")
            //simpan dan panggil SharePrefHelper
            var mySharedPref = DataSharedPref(context!!, PrefFileName)
            //mengambil data dari file sharedpref
            coin = mySharedPref.Rewardcoin!!
            member = mySharedPref.VIP_member!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var objView = inflater.inflate(R.layout.fragment_profile, container, false)
        var btnLogout = objView.findViewById<Button>(R.id.btn_logout)
        var btnWatchAds = objView.findViewById<Button>(R.id.btn_watchAds_profile)
        var tv_coin = objView.findViewById<TextView>(R.id.tv_coin_profile)
        var tv_member = objView.findViewById<TextView>(R.id.tv_member)
        var btnUpgrade = objView.findViewById<Button>(R.id.btn_upgrade)


        tv_coin.setText(coin.toString())

        //cek apakah user memiliki member VIP atau tidak, jika mmeber VIP maka true
        if(member) {
            tv_member.setText("Member : VIP")
            btnUpgrade.setText("Cancel")
        }
        else {
            tv_member.setText("Member : Standard")
            btnUpgrade.setText("Upgrade")
            //Load Rewarded Ads
            loadAd()
        }
        btnWatchAds.isEnabled = false



        btnLogout.setOnClickListener {
            getActivity()?.finish()
//            activity?.onBackPressed()
        }

        btnWatchAds.setOnClickListener{
            //ketika btn watch di klik, maka panggil function untuk menampilkan rewarded ads
            showRewardAds()
        }

        btnUpgrade.setOnClickListener{
            var mySharedPref = DataSharedPref(context!!, PrefFileName)
            //cek apakah user memiliki member VIP atau tidak, jika mmeber VIP maka true
            if(member) {
                member = false
                tv_member.setText("Member : Standard")
                mySharedPref.VIP_member = member
                btnUpgrade.setText("Upgrade")
                btnWatchAds.isEnabled = false
                //Load Rewarded Ads
                loadAd()
            }
            else {
                member = true
                tv_member.setText("Member : VIP")
                mySharedPref.VIP_member = member
                btnUpgrade.setText("Cancel")
            }
        }




//        val intentFilter = IntentFilter(QUOTES_SIGNAL)
//        //mendaftarkan receiver agar ProfileFragment dapat menangkap broadcast
//        requireActivity().registerReceiver(QuotesReceiver,intentFilter)
//        //panggil fungsi startMyJob untuk menjalankan job
//        startMyJob()
        return objView
    }

    override fun onDestroy() {
        super.onDestroy()
        //menghentikan job
//        cancelMyJob()
    }


//    private fun startMyJob() {
//        //definisikan service untuk menjalankan QuoteScheduler pada fragment ini
//        var serviceComponent = ComponentName(requireContext(), QuotesScheduler::class.java)
//        //buat JobInfo dan berikan JobID
//        //berikan informasi tambahan yaitu dengan Network job dapat dijalankan
//        val JobInfo = JobInfo.Builder(JobId, serviceComponent)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//
//        //definisikan job scheduler
//        var JobQoutes = activity?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//        //jalankan job schedulernya
//        JobQoutes.schedule(JobInfo.build())
//    }

//    private fun cancelMyJob() {
//        var JobQoutes = activity?.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//        //menghentikan job schedulernya dengan ID JobId
//        JobQoutes.cancel(JobId)
//    }


    //Load Rewarded Ads
    fun loadAd(){
        //inisialisasi adRequest agar dapat mengambil iklan dari google ad manager.
        var adRequest = AdRequest.Builder().build()
        //setelah itu, untuk memuat iklan, kita panggil load dan kirimkan parameter yg dibutuhkan.
        //RewardedAdLoadCallback akan digunakan untuk menerima iklan yang berhasil dimuat atau menerima error
        RewardedAd.load(context, "ca-app-pub-3940256099942544/5224354917",
                adRequest, object : RewardedAdLoadCallback() {
            //ketika iklan gagal di Load
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.d("Rewarded Ads", p0?.message)
                Toast.makeText(context, "Iklan gagal di Load", Toast.LENGTH_SHORT).show()
                //karena iklan gagal dimuat, maka kita kembalikan null
                mRewardVid = null
                //atur agar btn watch tidak dapat diklik karena tidak ada iklan yang dapat ditampilkan nantinya
                btn_watchAds_profile.isEnabled = true
            }

            //ketika iklan berhasil di Load
            override fun onAdLoaded(p0: RewardedAd) {
                super.onAdLoaded(p0)
                Log.d("Rewarded Ads", "Rewarded Ads berhasil di Load")
                //atur btn watch kembali bisa di klik agar user dpt menonton iklan
                btn_watchAds_profile.isEnabled = true
                //kembalikan iklan yang berhasil dimuat untuk ditampilkan nantinya
                mRewardVid = p0
            }
        })
    }

    // fungsi utk menampilkan iklan
    private fun showRewardAds() {
        //inisialisasi var ReawrdCoin untuk menampilkna jumlah coin yang telah dikumpukna user
        var RewardCoin = coin
        //lakukan pengeeckan terlebih dahulu apakah ada iklan yang dapat di tampilkan
        if (mRewardVid != null) {
            //buat FullScreenContentCallback untuk dipanggil ketika iklan ditampilkan dan ditutup
            mRewardVid?.fullScreenContentCallback = object : FullScreenContentCallback() {
                //ketika user menutup iklan
                override fun onAdDismissedFullScreenContent() {
                    Log.d("Rewarded Ads", "Ad was dismissed.")
                    //jangan lupa mengganti mRewardVid menjadi null agar iklan tidak ditampilakn 2x
                    mRewardVid = null
                    //atur btn agar tidak dapat diklik
                    btn_watchAds_profile.isEnabled = false
                    //setelah itu, jangan lupa utk memuat iklan baru lagi
                    loadAd()
                }
                //ketika iklan gagal ditampilkan
                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("Rewarded Ads", "Ad failed to show.")
                    //jgn lupa mengganti mRewardVid menjadi null agar iklan tidak ditampilakn lagi
                    mRewardVid = null
                }
                //ketika iklan  ditampilkan
                override fun onAdShowedFullScreenContent() {
                    Log.d("Rewarded Ads", "Ad showed fullscreen content.")
                }
            }
            //tampilkan Rewarded Ads nya
            mRewardVid?.show(activity, OnUserEarnedRewardListener() {
                //setelah menonton iklan, coin user akan bertambah 100
                RewardCoin += 100
                coin = RewardCoin
                //update coin terbaru user
                tv_coin_profile.setText(RewardCoin.toString())
                var mySharedPref = DataSharedPref(context!!, PrefFileName)
                //update data terbaru ke file sharedpref
                mySharedPref.Rewardcoin = coin
            })
        } else {
            Log.d("Rewarded Ads", "Ad wasn't loaded.")
//            Toast.makeText(context, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
            //karena tidak ada iklan, maka kita akan memuat iklan baru
            loadAd()
        }
    }

}