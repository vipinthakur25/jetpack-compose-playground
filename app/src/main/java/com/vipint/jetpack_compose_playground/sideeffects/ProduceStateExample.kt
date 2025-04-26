package com.vipint.jetpack_compose_playground.sideeffects

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementing produce state for data fetching
 *   {
 *
 *    To demonstrate the practical use of produceState in a real-world scenario,
 *    consider a simple application that fetches and displays a list of posts from a remote server.
 *
 *    }
 *
 */

/**
 * Setting Up the Data Layer
 * First, define a repository layer that simulates fetching posts from a remote source using Kotlin's Flow:
 */
data class Post(val title: String, val content: String)
class PostRepository {
    fun getPosts(): Flow<List<Post>> = flow {
        // Simulate network delay
        delay(1000)
        emit(listOf(Post("Title 1", "Content 1"), Post("Title 2", "Content 2")))
    }
}

@Composable
fun PostListScreen(
    modifier: Modifier = Modifier,
    postRepository: PostRepository = PostRepository()
) {

    //using produceState to manage the state of our post
    val postProduceState = produceState(initialValue = emptyList<Post>(), producer = {
        //fetch post asynchronously
        val postFlow = postRepository.getPosts()
        postFlow.collect { posts ->
            value = posts
        }
    })

    if (postProduceState.value.isEmpty()) {
        Text("Loading posts...")
    } else {
        LazyColumn {
            items(postProduceState.value) {
                Text(text = "Post : ${it.title}")
            }
        }
    }
}
