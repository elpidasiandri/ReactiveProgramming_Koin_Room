package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.myapplication.db.LocalDatabase
import com.example.myapplication.db.entinties.FollowersEntity
import com.example.myapplication.db.entinties.FollowingEntity
import com.example.myapplication.views.followersfollowing.AFollowingFollowersScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var transitionLauncher: ActivityResultLauncher<Intent>
    private lateinit var localDatabase: LocalDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_start)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        localDatabase = Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java, "database"
        )
            .build()
        insertSampleData()
        transitionLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            }

        transitionLauncher.launch(AFollowingFollowersScreen.newIntent(context = this))
           startActivity(AFollowingFollowersScreen.newIntent(context = this))
    }


    private fun insertSampleData() {
        followingDatabaseList()
        followersDatabaseList()
    }

    private fun followersDatabaseList() {
        val follower1 = FollowersEntity(
            id = 1,
            name = "Ken",
            userName = "Ken",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Ken Ken Ken",
            avatarUrl = ""
        )

        val follower2 = FollowersEntity(
            id = 2,
            name = "Panos",
            userName = "Panos",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Panos",
            avatarUrl = "https://www.animalsaroundtheglobe.com/wp-content/uploads/2023/02/ray-shrewsberry-ndPI6KgxcQg-unsplash-e1676370211442-1200x800.jpg.webp"
        )
        val follower3 = FollowersEntity(
            id = 3,
            name = "TestingTest",
            userName = "TestingTest",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = false,
            lastName = "TestingTest",
            avatarUrl = "https://media.real.gr/filesystem/images/20211209/low/968_252863_290755.JPG"
        )
        val follower4 = FollowersEntity(
            id = 4,
            name = "Test",
            userName = "Test",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = false,
            lastName = "Test",
            avatarUrl = "https://i0.wp.com/post.psychcentral.com/wp-content/uploads/sites/4/2022/08/hand-stroking-petting-tabby-cat-1296x728-header-1024x575.jpg?w=1155&h=1528"
        )
        val follower5 = FollowersEntity(
            id = 5,
            name = "gdskjfkdjhgkjdhfkjgndfkjghdkfhgdkfjhgdkfhgkdjfgd",
            userName = "gds",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "gdskjfkdjhgkjdhfkjgndfkjghdkfhgdkfjhgdkfhgkdjfgd",
            avatarUrl = ""
        )
        val follower6 = FollowersEntity(
            id = 6,
            name = "LALALALAALLA",
            userName = "lala",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "allala",
            avatarUrl = ""
        )
        val follower7 = FollowersEntity(
            id = 7,
            name = "Pan",
            userName = "Pa",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Pas",
            avatarUrl = ""
        )
        val follower8 = FollowersEntity(
            id = 8,
            name = "gikuhdsgikgnjdkfgdsfhgfgdgffgdfgdf",
            userName = "Panos",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Panos",
            avatarUrl = "https://www.tovima.gr/wp-content/uploads/2022/10/30/26944305_alienearth.jpg"
        )
        val follower9 = FollowersEntity(
            id = 9,
            name = "elpida",
            userName = "elpida",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "elpida",
            avatarUrl = ""
        )
        val follower10 = FollowersEntity(
            id = 10,
            name = "testiffffffffffffffffffffffffffffffffng",
            userName = "tes",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "tess",
            avatarUrl = "https://www.ekriti.gr/sites/default/files/styles/max_1300x1300/public/2022-05/1576682958_https_2f-2fmoviewriternyu.files.wordpress.com-2f2014-2f05-2faliens1.jpg?itok=ImYCQICd"
        )
        val follower11 = FollowersEntity(
            id = 11,
            name = "jfshajfksjhgdsakjhfdkjhfdsk",
            userName = "GG",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "GG",
            avatarUrl = "https://ideascdn.lego.com/media/generate/entity/lego_ci/project/3b7ef813-5a42-418d-bf96-07e58b61d132/6/resize:1600:900/legacyp"
        )
        val follower12 = FollowersEntity(
            id = 12,
            name = "Panos",
            userName = "Panos",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Panos",
            avatarUrl = "https://diamondpet.storage.googleapis.com/wp-content/uploads/2019/04/01143720/cat-lying-on-grass-020123.jpg"
        )

        GlobalScope.launch {
            try {
                localDatabase.followersListDao().insert(follower1)
                localDatabase.followersListDao().insert(follower2)
                localDatabase.followersListDao().insert(follower3)
                localDatabase.followersListDao().insert(follower4)
                localDatabase.followersListDao().insert(follower5)
                localDatabase.followersListDao().insert(follower6)
                localDatabase.followersListDao().insert(follower7)
                localDatabase.followersListDao().insert(follower8)
                localDatabase.followersListDao().insert(follower9)
                localDatabase.followersListDao().insert(follower10)
                localDatabase.followersListDao().insert(follower11)
                localDatabase.followersListDao().insert(follower12)

            } catch (e: Exception) {
                Log.d("Q12345", "follower error = ${e.message}")
            }

        }
    }

    private fun followingDatabaseList() {
        val following1 = FollowingEntity(
            id = 1,
            name = "Maria Papadopoulou",
            userName = "Maria Maria",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Papa dopoulopu",
            avatarUrl = "https://www.hindustantimes.com/ht-img/img/2023/07/10/550x309/labrador-retriever-gfd78b67cf_1280_1677927949246_1688982230758.jpg"
        )
        val following2 = FollowingEntity(
            id = 2,
            name = "George Papadopoulos",
            userName = "George George",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Papadopoulos Papadopoulos",
            avatarUrl = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-674010.jpg&fm=jpg"
        )
        val following3 = FollowingEntity(
            id = 3,
            name = "ELPI ELPI",
            userName = "Siandri Siandri",
            isVerifiedCreator = false,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Papadopoulos Papadopoulos",
            avatarUrl = "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-674010.jpg&fm=jpg"
        )

        val following4 = FollowingEntity(
            id = 4,
            name = "ELPI ELPI",
            userName = "Siandri Siandri",
            isVerifiedCreator = false,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Siandri ELPI",
            avatarUrl = "https://i.natgeofe.com/n/9135ca87-0115-4a22-8caf-d1bdef97a814/75552_square.jpg"
        )

        val following5 = FollowingEntity(
            id = 5,
            name = "maria mary",
            userName = "mary mary",
            isVerifiedCreator = false,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Maria Mary",
            avatarUrl = "https://cat-world.com/wp-content/uploads/2017/07/healthy-cat-eyes.jpg"
        )
        val following6 = FollowingEntity(
            id = 6,
            name = "gg",
            userName = "gg",
            isVerifiedCreator = false,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "gg",
            avatarUrl = "https://static.independent.co.uk/2023/03/13/14/28a981486ac0e011326aa4776ed7c9b7Y29udGVudHNlYXJjaGFwaSwxNjc4ODAzMjk0-2.58413464.jpg"
        )

        val following7 = FollowingEntity(
            id = 7,
            name = "AAAAAA",
            userName = "ABCDEF",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "ABDCEF",
            avatarUrl = ""
        )

        val following8 = FollowingEntity(
            id = 8,
            name = "Hope hope hope",
            userName = "hohosisjkmfdskjfdskjgfdskjgkdsfujgksdfbhkjdsfnbjkdsfbfbds",
            isVerifiedCreator = false,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "ABDCdsfsefsfjdfkjgdfkjdkjgfkjdgfkjdgfkjdgxfkjdgfEF",
            avatarUrl = ""
        )
        val following9 = FollowingEntity(
            id = 9,
            name = "papapapapapapapapapapaa",
            userName = "papapapapapapapapapapaa",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "papapapapapapapapapapaa",
            avatarUrl = ""
        )
        val following10 = FollowingEntity(
            id = 10,
            name = "maria",
            userName = "maria",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = true,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "maria",
            avatarUrl = "https://media.cnn.com/api/v1/images/stellar/prod/230426143017-sand-cat-card.jpg?c=4x3"
        )
        val following11 = FollowingEntity(
            id = 11,
            name = "lalalalalalalalaalalallaa",
            userName = "lalalalalalalalaalalallaa",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "lalalalalalalalaalalallaa",
            avatarUrl = ""
        )
        GlobalScope.launch {
            try {
                localDatabase.followingListDao().insert(following1)
                localDatabase.followingListDao().insert(following2)
                localDatabase.followingListDao().insert(following3)
                localDatabase.followingListDao().insert(following4)
                localDatabase.followingListDao().insert(following5)
                localDatabase.followingListDao().insert(following6)
                localDatabase.followingListDao().insert(following7)
                localDatabase.followingListDao().insert(following8)
                localDatabase.followingListDao().insert(following9)
                localDatabase.followingListDao().insert(following10)
                localDatabase.followingListDao().insert(following11)
            } catch (e: Exception) {
                Log.d("Q12345", "error = ${e.message}")
            }

        }
    }
}

