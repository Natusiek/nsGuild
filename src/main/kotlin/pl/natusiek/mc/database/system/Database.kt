package pl.natusiek.mc.database.system

import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import org.bson.Document
import pl.natusiek.mc.common.configuration.ConfigLoader
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Database(
    private val uri: MongoClientURI,
    private val databaseName: String
) {

    private val client: MongoClient = MongoClient(this.uri)
    val database: MongoDatabase = this.client.getDatabase(this.databaseName)

    val executor: ExecutorService = Executors.newSingleThreadExecutor(ThreadFactoryBuilder().setNameFormat("Database Executor").build())

    fun <T : DatabaseEntity> insertEntity(entity: T) {
        this.executor.execute {
            val collection = this.database.getCollection(entity.collection)

            if (collection.countDocuments() < 0) {
                this.database.createCollection(entity.collection)
            }

            collection.insertOne(Document.parse(ConfigLoader.GSON.toJson(entity)))
        }
    }

    fun <T : DatabaseEntity> updateEntity(entity: T) {
        this.executor.execute {
            this.database.getCollection(entity.collection).findOneAndReplace(Document(entity.key, entity.id), Document.parse(ConfigLoader.GSON.toJson(entity)))
        }
    }

    fun <T : DatabaseEntity> deleteEntity(entity: T) {
        this.executor.execute {
            this.database.getCollection(entity.collection).deleteOne(Document(entity.key, entity.id))
        }
    }

    inline fun <reified T> loadBySelector(name: String, id: String, selector: String, crossinline listener: (data: T?) -> Unit) {
        this.executor.execute {
            try {
                val document = this.database.getCollection(name)
                    .find(Document(selector, id))
                    .first()

                if (document != null)
                    listener.invoke(ConfigLoader.GSON.fromJson(ConfigLoader.GSON.toJson(document), T::class.java))
                else
                    listener.invoke(null)
            } catch (ex: Throwable) {
                listener.invoke(null)
            }
        }
    }

    inline fun <reified T> load(name: String, id: String, crossinline listener: (data: T?) -> Unit) {
        this.executor.execute {
            try {
                val document = this.database.getCollection(name)
                    .find(Document("id", id))
                    .first()

                if (document != null)
                    listener.invoke(ConfigLoader.GSON.fromJson(ConfigLoader.GSON.toJson(document), T::class.java))
                else
                    listener.invoke(null)
            } catch (ex: Throwable) {
                listener.invoke(null)
            }
        }
    }

    inline fun <reified T> loadAll(name: String, crossinline listener: (data: Set<T>) -> Unit) {
        this.executor.execute {
            try {
                val list = HashSet<T>()
                this.database.getCollection(name)
                    .find()
                    .forEach {
                        list.add(ConfigLoader.GSON.fromJson(ConfigLoader.GSON.toJson(it), T::class.java))
                    }

                listener(list)
            } catch (ex: Throwable) {
                listener.invoke(HashSet())
            }
        }
    }

    fun removeCollection(name: String) {
        this.executor.execute {
            this.database.getCollection(name).drop()
        }
    }

}