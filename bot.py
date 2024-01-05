from telegram.ext import (
    ApplicationBuilder,
    CallbackContext,
    CommandHandler,
)
from telegram import (
    Update,
)
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
import asyncio
import textwrap
'''
client = MongoClient("mongodb+srv://ArashAri44:ArashAri44@cluster0.eeti8di.mongodb.net/?retryWrites=true&w=majority&tls=true&tlsAllowInvalidCertificates=true", server_api=ServerApi('1'))
db = client["telegram_bot_db"]
collection = db["users"]
'''
async def start(update: Update, _: CallbackContext) -> None:
    await update.message.reply_text(textwrap.dedent("""
        use:
        /save user_id 
        
        to make user_id enable to send you messages from SOS app here when needs your help
    """))

async def save_user(update: Update, context: CallbackContext) -> None:
    if len(context.args) != 1:
        await update.message.reply_text(textwrap.dedent("""
            please enter the user_id
        """))
        return
    user_id = context.args[0]
    chat_id = str(update.message.chat_id)
    print(user_id)
    print(chat_id)
    '''data = {"user_id": user_id, "chat_id": chat_id}
    collection.insert_one(data)'''
    await update.message.reply_text(textwrap.dedent("""
        user data saved successfully
        """))

def main():
    token = "6477692240:AAGTWzQHKM7syt8q6fX2lZrQ3jjGKXMx8AQ"
    app = ApplicationBuilder().token(token).build()
    app.add_handler(CommandHandler("start", start))
    app.add_handler(CommandHandler("save", save_user))
    loop = asyncio.new_event_loop()
    asyncio.set_event_loop(loop)
    loop.run_until_complete(app.bot.set_my_commands(
        [
            ("start", "guide"),
            ("save", "aving user_id for getting help messages"),
        ]
    ))
    app.run_polling()

if __name__ == "__main__":
    main()

