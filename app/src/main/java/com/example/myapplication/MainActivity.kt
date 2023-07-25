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
//            friendRequest = null,
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
         //   friendRequest = null,
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
        //    friendRequest = null,
            avatarUrl = "https://www.animalsaroundtheglobe.com/wp-content/uploads/2023/02/ray-shrewsberry-ndPI6KgxcQg-unsplash-e1676370211442-1200x800.jpg.webp"
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
     //       friendRequest = null,
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
          //  friendRequest = null,
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
        //    friendRequest = null,
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
      //      friendRequest = null,
            avatarUrl = ""
        )
        val follower8 = FollowersEntity(
            id = 8,
            name = "Panos",
            userName = "Panos",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "Panos",
        //    friendRequest = null,
            avatarUrl = "https://www.animalsaroundtheglobe.com/wp-content/uploads/2023/02/ray-shrewsberry-ndPI6KgxcQg-unsplash-e1676370211442-1200x800.jpg.webp"
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
    //        friendRequest = null,
            avatarUrl = ""
        )
        val follower10 = FollowersEntity(
            id = 10,
            name = "testing",
            userName = "tes",
            isVerifiedCreator = true,
            hasFriendRequest = false,
            status = "not_requested",
            isFriends = false,
            friendRequestIsOut = false,
            amIfollowing = true,
            lastName = "tess",
  //          friendRequest = null,
            avatarUrl = "https://www.animalsaroundtheglobe.com/wp-content/uploads/2023/02/ray-shrewsberry-ndPI6KgxcQg-unsplash-e1676370211442-1200x800.jpg.webp"
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
     //       friendRequest = null,
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
     //       friendRequest = null,
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
         //   friendRequest = null,
            avatarUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fimage&psig=AOvVaw28KWFkTK71gX1fNhJy_BnC&ust=1690187972716000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOiV3v-2pIADFQAAAAAdAAAAABAE"
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
        //    friendRequest = null,
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
          //  friendRequest = null,
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
        //    friendRequest = null,
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
    //        friendRequest = null,
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
       //     friendRequest = null,
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
        //    friendRequest = null,
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
       //     friendRequest = null,
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
       //     friendRequest = null,
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
        //    friendRequest = null,
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
          //  friendRequest = null,
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

//    private fun readSampleData() {
//        // Retrieving all authors and books
//        GlobalScope.launch {
//            val authors = localDatabase.authorDao().getA()
//            val books = booksDatabase.bookDao().getAllBooks()
//
//            // Do something with the retrieved data (e.g., display it on UI)
//            // ...
//        }
//    }

}
